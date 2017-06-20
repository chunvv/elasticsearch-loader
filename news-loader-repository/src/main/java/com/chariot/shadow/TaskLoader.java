package com.chariot.shadow;

import com.chariot.shadow.executor.ShadowQueue;
import com.chariot.shadow.indexing.Index;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by Trung Vu on 2017/06/06.
 */
@RequiredArgsConstructor
@Getter
public abstract class TaskLoader<T> implements Task {

    protected ShadowQueue queue;
    @NonNull
    protected T id;

    @Override
    public Index call() throws Exception {
        queue.start();
        Index index = load();
        queue.end();
        return index;
    }
}
