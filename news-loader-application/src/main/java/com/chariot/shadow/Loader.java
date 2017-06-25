package com.chariot.shadow;

import com.chariot.shadow.config.Elasticsearch;
import com.chariot.shadow.executor.ElasticsearchThread;
import com.chariot.shadow.executor.Message;
import com.chariot.shadow.executor.ShadowQueue;
import com.chariot.shadow.executor.ShadowThreadPoolExecutor;
import com.chariot.shadow.indexing.Index;
import com.chariot.shadow.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.Client;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * Created by Trung Vu on 2017/06/12.
 */
@NoArgsConstructor
@Getter
@Slf4j
public abstract class Loader implements Loadable {

    public static final String INDEX_NAME = "news";
    private static int WAITING_THRESHOLD_PAUSE = 30;
    private static int WAITING_THRESHOLD_RESUME = 20;

    private int queueSize;
    private int executorSize;
    protected Client client;

    protected ShadowThreadPoolExecutor executor;
    protected ShadowQueue queue;
    protected LinkedList<Future<Index>> futures;
    protected ExecutorService indexingExecutor = Executors.newFixedThreadPool(2);

    @Override
    public void init() {
        client = Elasticsearch.getInstance();
        queueSize = 10;
        executorSize = 10;
        executor = new ShadowThreadPoolExecutor(executorSize);
        queue = new ShadowQueue(queueSize);
        log.info("Starting Loader with Queue size is: " + queueSize + " and executor size is: " + executorSize);
    }

    @Override
    public void run() throws ExecutionException, InterruptedException {
        init();
        futures = submit();
        executor.shutdown();
        Future<Index> future;
        while ((future = futures.pollFirst()) != null) {
            future.get().forEach(index -> queue.add(index));
            if (queue.hasExecutable()) {
                beforeExecute();
                indexing();
                afterExecute();
            }
        }
        queue.close();
        indexing();
        shutdown();
    }

    @Override
    public void shutdown() throws InterruptedException {
        executor.shutdown();
        indexingExecutor.shutdown();
        log.info("Shutting down loader");
    }

    @Override
    public void beforeExecute() {
        if (queue.waitingSize() > WAITING_THRESHOLD_PAUSE && !executor.isPaused()) {
            executor.pause();
            log.info("Paused Executor");
        }
    }

    @Override
    public void afterExecute() {
        if (queue.waitingSize() < WAITING_THRESHOLD_RESUME && executor.isPaused()) {
            executor.resume();
            log.info("Resumed Executor");
        }
    }

    @Override
    public void indexing() {
        List<Item> items;
        while ((items = queue.pollFirst()) != null) {
            indexingExecutor.execute(new ElasticsearchThread(Optional.of(this), items));
            updateUpdateSign(items);
        }
    }

    @Override
    public void updateStatus(Message message) {
        queue.increaseComplete(message.getSize());
    }
}
