package com.chariot.shadow.news;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import org.junit.Test;

/**
 * Created by Trung Vu on 2017/06/26.
 */
public class NewsExecutorTest {

    @Tested NewsExecutor newsExecutor;

    @Test
    public void executeNewsLoader(@Mocked NewsLoader newsLoader) throws Exception {
        new Expectations() {{
            newsLoader.run();
        }};
        newsExecutor.execute();
    }
}