package com.chariot.shadow.news;

import com.chariot.shadow.news.util.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Trung Vu on 2017/06/20.
 */
public class NewsRepository {

    @Autowired
    private NewsInfrastructure newsInfrastructure;

    public List<News> retrieveInsertNews() {
        return newsInfrastructure.retrieveInsertNews().stream().map(NewsMapper::map).collect(Collectors.toList());
    }

    public List<News> retrieveDeleteNews() {
        return newsInfrastructure.retrieveDeleteNews().stream().map(NewsMapper::map).collect(Collectors.toList());
    }
}
