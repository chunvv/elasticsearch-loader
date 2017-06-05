package com.chariot.shadow.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Threads executor to control all threads
 * <p>
 * Created by Trung Vu on 2017/06/05.
 */
public class ShadowTheadExecutor extends ThreadPoolExecutor {

    private boolean paused;

    private ReentrantLock lock;
    private Condition condition;

    public ShadowTheadExecutor(int numberOfThreads) {
        super(numberOfThreads, numberOfThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public boolean isPaused() {
        return paused;
    }

    @Override
    protected void beforeExecute(Thread thread, Runnable runnable) {
        super.beforeExecute(thread, runnable);
        lock.lock();
        await(thread);
        lock.unlock();
    }

    public void pause() {
        lock.lock();
        paused = true;
        lock.unlock();
    }

    public void resume() {
        lock.lock();
        paused = false;
        condition.signalAll();
        lock.unlock();
    }

    private void await(Thread thread) {
        while (paused) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                thread.interrupt();
            }
        }
    }
}
