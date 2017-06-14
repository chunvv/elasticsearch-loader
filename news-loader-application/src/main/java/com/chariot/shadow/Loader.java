package com.chariot.shadow;

import com.chariot.shadow.executor.ShadowQueue;
import com.chariot.shadow.executor.ShadowThreadPoolExecutor;
import com.chariot.shadow.indexing.Index;
import com.chariot.shadow.item.Item;
import org.elasticsearch.client.Client;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Trung Vu on 2017/06/12.
 */
public abstract class Loader implements Loadable {

    private int size;
    protected Client client;

    private static int WAITING_THRESHOLD_PAUSE = 300;
    private static int WAITING_THRESHOLD_RESUME = 200;

    private ShadowThreadPoolExecutor executor;
    private ShadowQueue queue;
    private LinkedList<Future<Index>> futures;
    private ExecutorService indexingExecutor = Executors.newFixedThreadPool(2);


    @Override
    public void init() {
        size = 100;
        executor = new ShadowThreadPoolExecutor(size);
    }

    @Override
    public void run() throws ExecutionException, InterruptedException {
        init();
        beforeExecute();
        futures = submit();
        executor.shutdown();
        Future<Index> future;
        while ((future = futures.pollFirst()) != null) {
            future.get().forEach(index -> queue.add(index));
            if (queue.hasExecutable()) {
                afterExecute();
                indexing();
            }
        }
        queue.close();
    }

    @Override
    public void shutdown() {
        executor.shutdown();
    }

    @Override
    public void beforeExecute() {
        if (queue.waitingSize() > WAITING_THRESHOLD_PAUSE) {
            executor.pause();
        }
    }

    @Override
    public void afterExecute() {
        if (queue.waitingSize() < WAITING_THRESHOLD_RESUME) {
            executor.resume();
        }
    }

    @Override
    public void indexing() {
        List<Item> items;
        while (queue.waitingSize() < WAITING_THRESHOLD_RESUME && (items = queue.pollFirst()) != null) {
            indexingExecutor.execute(execute(items));
        }
    }

    @Override
    public Runnable execute(List<Item> items) {
        return null;
    }
}
