package com.chariot.shadow.task;

import com.chariot.shadow.executor.ShadowQueue;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * Created by Trung Vu on 2017/06/06.
 */
@Value
@RequiredArgsConstructor
public abstract class TaskLoader<T> implements Loader {

    protected ShadowQueue queue;
    @NonNull
    protected T id;

    @Override
    public Object call() throws Exception {
        queue.startLoading();
        return load();
    }
}
