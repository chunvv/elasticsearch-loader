package com.chariot.shadow.news;

import com.chariot.shadow.executor.ShadowQueue;
import com.chariot.shadow.id.Id;
import com.chariot.shadow.indexing.Index;
import com.chariot.shadow.item.IndexItem;
import com.chariot.shadow.item.IndexType;
import com.chariot.shadow.news.util.NewsMapBuilder;
import com.chariot.shadow.news.util.NewsMapper;
import mockit.*;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Trung Vu on 2017/06/26.
 */
public class NewsIndexTaskLoaderTest {
    
    @Tested NewsIndexTaskLoader newsIndexTaskLoader;
    @Injectable private NewsInfrastructure newsInfrastructure;
    @Injectable protected ShadowQueue queue;
    @Injectable protected Id id;

    @Test
    public void loadIndexTask(@Mocked NewsEntity newsEntity, @Mocked News news, @Mocked Index index, @Mocked NewsMapper newsMapper, @Mocked NewsMapBuilder newsMapBuilder) throws Exception {
        Deencapsulation.setField(newsIndexTaskLoader, "newsInfrastructure", newsInfrastructure);
        
        new Expectations(newsIndexTaskLoader) {{
            newsIndexTaskLoader.getId(); result = new Id("ID"); 
            newsInfrastructure.find("ID"); result = newsEntity;
            NewsMapper.map(newsEntity); result = news;
            NewsMapBuilder.build(news); result = new HashMap<>();
        }};
        
        Index actual = new Index();
        actual.add(new IndexItem(IndexType.IMPORT, new Id("ID"), new HashMap<>())); 
        
        assertThat(newsIndexTaskLoader.load(), Is.is(actual));
    }
}