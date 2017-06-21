package com.chariot.shadow.news;

import com.chariot.shadow.TaskLoader;
import com.chariot.shadow.executor.ShadowQueue;
import com.chariot.shadow.id.Id;
import com.chariot.shadow.indexing.Index;
import com.chariot.shadow.item.DeleteItem;
import com.chariot.shadow.item.IndexType;

/**
 * Created by Trung Vu on 2017/06/06.
 */
public class NewsDeleteTaskLoader extends TaskLoader<Id> {

    public NewsDeleteTaskLoader(ShadowQueue queue, Id id) {
        super(queue, id);
    }

    @Override
    public Index load() {
        Index index = new Index();
        index.add(new DeleteItem(IndexType.IMPORT, getId()));
        return index;
    }
}
