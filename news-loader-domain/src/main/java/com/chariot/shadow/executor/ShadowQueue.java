package com.chariot.shadow.executor;

import com.chariot.shadow.item.Item;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Trung Vu on 2017/06/06.
 */
@Value
public class ShadowQueue implements com.chariot.shadow.executor.Queue {

    private final int size;

    private Queue<List<Item>> queue;
    private List<Item> running;

    private AtomicInteger requested;
    private AtomicInteger completed;
    private AtomicInteger loading;

    public ShadowQueue(int size) {
        this.size = size;
        this.queue = new ConcurrentLinkedDeque<>();
        this.running = new ArrayList<>();
        this.requested = new AtomicInteger();
        this.completed = new AtomicInteger();
    }

    @Override
    public void start() {
        loading.getAndAdd(1);
    }

    @Override
    public void end() {
        loading.getAndAdd(-1);
    }
}
