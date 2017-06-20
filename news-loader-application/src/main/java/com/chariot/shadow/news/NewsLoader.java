package com.chariot.shadow.news;

import com.chariot.shadow.Loader;
import com.chariot.shadow.id.Id;
import com.chariot.shadow.indexing.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by Trung Vu on 2017/06/20.
 */
@Component
public class NewsLoader extends Loader {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public LinkedList<Future<Index>> submit() {
        LinkedList<Future<Index>> result = new LinkedList<>();
        result.addAll(submitIndexNews());
        result.addAll(submitDeleteNews());

        return result;
    }

    private LinkedList<Future<Index>> submitIndexNews() {
        return newsRepository.retrieveInsertNews().
                stream().
                map(news -> executor.
                        submit(new NewsIndexTaskLoader(new Id(news.getIdAsString())))).
                collect(Collectors.toCollection(LinkedList::new));
    }

    private LinkedList<Future<Index>> submitDeleteNews() {
        return newsRepository.retrieveDeleteNews().
                stream().
                map(news -> executor.
                        submit(new NewsDeleteTaskLoader(new Id(news.getIdAsString())))).
                collect(Collectors.toCollection(LinkedList::new));
    }
}
