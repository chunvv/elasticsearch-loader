package com.chariot.shadow.item;

import com.chariot.shadow.id.Id;
import lombok.Value;

/**
 * Created by Trung Vu on 2017/06/06.
 */
@Value
public class DeleteItem implements Item {

    private IndexType indexType;
    private Id id;

    public String id() {
        return id.getId();
    }

    public String getIndexTypeAsString() {
        return indexType.getType();
    }
}
