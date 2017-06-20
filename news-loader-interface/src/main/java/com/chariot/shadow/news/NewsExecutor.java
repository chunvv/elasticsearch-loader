package com.chariot.shadow.news;

import com.chariot.shadow.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.ExecutionException;

/**
 * Created by Trung Vu on 2017/06/20.
 */
public class NewsExecutor implements Executable {

    @Autowired
    private ApplicationContext context;

    @Override
    public void execute() throws ExecutionException, InterruptedException {
        NewsLoader newsLoader = context.getBean(NewsLoader.class);
        newsLoader.run();
    }
}
