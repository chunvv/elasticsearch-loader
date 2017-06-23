package com.chariot.shadow.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Trung Vu on 2017/06/12.
 */
public class ShadowThreadPoolExecutor extends ThreadPoolExecutor implements Executor {

    private boolean paused;

    private ReentrantLock lock = new ReentrantLock();
    private Condition waiter = lock.newCondition();

    public ShadowThreadPoolExecutor(int numberOfThreads) {
        super(numberOfThreads, numberOfThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        lock.lock();
        try {
            while (paused) {
                waiter.await();
            }
        } catch (InterruptedException ie) {
            t.interrupt();
        } finally {
            lock.unlock();
        }

    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
    }

    @Override
    public void pause() {
        lock.lock();
        try {
            paused = true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void resume() {
        lock.lock();
        try {
            paused = false;
            waiter.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isPaused() {
        return paused;
    }
}
