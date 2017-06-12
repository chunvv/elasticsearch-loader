package com.chariot.shadow.executor;

import lombok.Value;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Trung Vu on 2017/06/12.
 */
@Value
public class ShadowThreadPoolExecutor extends ThreadPoolExecutor implements Executor {

    // TODO - Attributes here

    public ShadowThreadPoolExecutor(int numberOfThreads) {
        super(numberOfThreads, numberOfThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        // TODO
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        // TODO
    }

    @Override
    public void pause() {
        // TODO
    }

    @Override
    public void resume() {
        // TODO
    }
}
