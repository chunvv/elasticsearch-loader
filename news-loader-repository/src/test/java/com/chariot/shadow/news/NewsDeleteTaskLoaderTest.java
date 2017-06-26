package com.chariot.shadow.news;

import com.chariot.shadow.executor.ShadowQueue;
import com.chariot.shadow.id.Id;
import com.chariot.shadow.indexing.Index;
import com.chariot.shadow.item.DeleteItem;
import com.chariot.shadow.item.IndexType;
import mockit.*;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Trung Vu on 2017/06/26.
 */
public class NewsDeleteTaskLoaderTest {

    @Tested NewsDeleteTaskLoader newsDeleteTaskLoader;
    @Injectable protected ShadowQueue queue;
    @Injectable protected Id id;

    @Test
    public void loadIndexTask(@Mocked News news, @Mocked Index index) throws Exception {

        new Expectations(newsDeleteTaskLoader) {{
            newsDeleteTaskLoader.getId(); result = new Id("ID");
        }};

        Index actual = new Index();
        actual.add(new DeleteItem(IndexType.IMPORT, new Id("ID")));

        assertThat(newsDeleteTaskLoader.load(), Is.is(actual));
    }
}