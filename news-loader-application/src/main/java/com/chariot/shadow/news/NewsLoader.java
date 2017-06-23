package com.chariot.shadow.news;

import com.chariot.shadow.Loader;
import com.chariot.shadow.id.Id;
import com.chariot.shadow.indexing.Index;
import com.chariot.shadow.item.Item;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by Trung Vu on 2017/06/20.
 */
public class NewsLoader extends Loader {

    private NewsRepository newsRepository = new NewsRepository();

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
                        submit(new NewsIndexTaskLoader(queue, new Id(news.getIdAsString())))).
                collect(Collectors.toCollection(LinkedList::new));
    }

    private LinkedList<Future<Index>> submitDeleteNews() {
        return newsRepository.retrieveDeleteNews().
                stream().
                map(news -> executor.
                        submit(new NewsDeleteTaskLoader(queue, new Id(news.getIdAsString())))).
                collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public void updateUpdateSign(List<Item> items) {
        items.forEach(item -> newsRepository.updateUpdateSign(new NewsID(item.id()), 0));
    }
}
