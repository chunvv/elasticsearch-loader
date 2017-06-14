package com.chariot.shadow.executor;

/**
 * Created by Trung Vu on 2017/06/14.
 */
public interface Notifier<T, K> {

    void attachObserver(T t);

    void detachObserver(T t);

    void notifyObserver(K k);
}
