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
import org.elasticsearch.client.Client;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Trung Vu on 2017/06/12.
 */
@NoArgsConstructor
@Getter
public abstract class Loader implements Loadable {

    public static final String INDEX_NAME = "news";
    private static int WAITING_THRESHOLD_PAUSE = 10;
    private static int WAITING_THRESHOLD_RESUME = 5;

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
        while (queue.indexingSize() < WAITING_THRESHOLD_RESUME && (items = queue.pollFirst()) != null) {
            indexingExecutor.execute(new ElasticsearchThread(Optional.of(this), items));
        }
    }

    @Override
    public void updateStatus(Message message) {
        queue.increaseComplete(message.getSize());
    }
}
