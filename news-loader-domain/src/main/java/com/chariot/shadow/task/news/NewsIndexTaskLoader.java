package com.chariot.shadow.task.news;

import com.chariot.shadow.id.Id;
import com.chariot.shadow.indexing.Index;
import com.chariot.shadow.task.TaskLoader;
import lombok.Value;

/**
 * Created by Trung Vu on 2017/06/06.
 */
@Value
public class NewsIndexTaskLoader extends TaskLoader<Id> {

    @Override
    public Index load() {
        return null;
    }
}
