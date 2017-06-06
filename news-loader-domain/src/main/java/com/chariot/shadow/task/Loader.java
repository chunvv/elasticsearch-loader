package com.chariot.shadow.task;

import com.chariot.shadow.indexing.Index;

import java.util.concurrent.Callable;

/**
 * Created by Trung Vu on 2017/06/06.
 */
public interface Loader<T> extends Callable<Index> {

    Index load();
}
