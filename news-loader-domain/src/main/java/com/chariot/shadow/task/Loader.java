package com.chariot.shadow.task;

import java.util.concurrent.Callable;

/**
 * Created by Trung Vu on 2017/06/06.
 */
public interface Loader<T> extends Callable<T> {

    T load();
}
