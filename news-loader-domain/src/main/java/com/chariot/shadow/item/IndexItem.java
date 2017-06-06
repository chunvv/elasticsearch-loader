package com.chariot.shadow.item;

import com.chariot.shadow.id.Id;
import com.chariot.shadow.parent.Parent;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Map;

/**
 * Created by Trung Vu on 2017/06/06.
 */
@Value
@AllArgsConstructor
public class IndexItem implements Item {

    private IndexType indexType;
    private Id id;
    private Map<String, Object> source;
    private Parent parent;

    public IndexItem(IndexType indexType, Id id, Map<String, Object> source) {
        this(indexType, id, source, null);
    }

    public String id() {
        return id.getId();
    }
}
