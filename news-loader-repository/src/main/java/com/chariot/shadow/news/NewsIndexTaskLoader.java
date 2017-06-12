package com.chariot.shadow.news;

import com.chariot.shadow.TaskLoader;
import com.chariot.shadow.id.Id;
import com.chariot.shadow.indexing.Index;
import com.chariot.shadow.item.IndexItem;
import com.chariot.shadow.item.IndexType;
import com.chariot.shadow.news.util.MapBuilder;
import com.chariot.shadow.news.util.NewsMapper;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Trung Vu on 2017/06/06.
 */
@Value
@Component
public class NewsIndexTaskLoader extends TaskLoader<Id> {

    @Autowired
    private NewsInfrastructure newsInfrastructure;

    @Override
    public Index load() {
        Index index = new Index();
        index.add(
                new IndexItem(
                        IndexType.IMPORT,
                        getId(),
                        MapBuilder.build(
                                NewsMapper.map(newsInfrastructure.find(getId().getId())))));
        return index;
    }
}
