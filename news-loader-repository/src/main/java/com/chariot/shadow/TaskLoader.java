package com.chariot.shadow;

import com.chariot.shadow.executor.ShadowQueue;
import com.chariot.shadow.indexing.Index;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Trung Vu on 2017/06/06.
 */
@Getter
@AllArgsConstructor
public abstract class TaskLoader<T> implements Task {

    protected ShadowQueue queue;
    protected T id;

    @Override
    public Index call() throws Exception {
        queue.start();
        Index index = load();
        queue.end();
        return index;
    }
}
