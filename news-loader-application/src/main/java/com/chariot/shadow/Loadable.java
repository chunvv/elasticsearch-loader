package com.chariot.shadow;

import com.chariot.shadow.indexing.Index;
import com.chariot.shadow.item.Item;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Trung Vu on 2017/06/12.
 */
public interface Loadable {

    void init();

    LinkedList<Future<Index>> submit();

    void run() throws ExecutionException, InterruptedException;

    void shutdown();

    void beforeExecute();

    void afterExecute();

    void indexing();

    Runnable execute(List<Item> items);
}
