package com.chariot.shadow.news;

import com.chariot.shadow.Executable;

import java.util.concurrent.ExecutionException;

/**
 * Created by Trung Vu on 2017/06/20.
 */
public class NewsExecutor implements Executable {

    @Override
    public void execute() throws ExecutionException, InterruptedException {
        new NewsLoader().run();
    }
}
