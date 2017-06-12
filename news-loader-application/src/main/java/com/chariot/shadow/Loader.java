package com.chariot.shadow;

import com.chariot.shadow.executor.ShadowThreadPoolExecutor;
import org.elasticsearch.client.Client;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Trung Vu on 2017/06/12.
 */
public abstract class Loader<T> implements Loadable {

    protected int size;
    protected Client client;

    protected ShadowThreadPoolExecutor executor;
    protected List<Future<T>> futures;

    @Override
    public void init() {
        size = 100;
        executor = new ShadowThreadPoolExecutor(size);
    }

    @Override
    public void run() {
        futures = submit();
    }

    @Override
    public void shutdown() {
        executor.shutdown();
    }
}
