package com.chariot.shadow.task.news;

import com.chariot.shadow.id.Id;
import com.chariot.shadow.indexing.Index;
import com.chariot.shadow.item.DeleteItem;
import com.chariot.shadow.item.IndexType;
import com.chariot.shadow.task.TaskLoader;
import lombok.Value;

/**
 * Created by Trung Vu on 2017/06/06.
 */
@Value
public class NewsDeleteTaskLoader extends TaskLoader<Id> {
    @Override
    public Index load() {
        Index index = new Index();
        index.add(new DeleteItem(IndexType.IMPORT, getId()));
        return index;
    }
}
