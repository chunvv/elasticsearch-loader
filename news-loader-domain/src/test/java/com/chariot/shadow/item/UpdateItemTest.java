package com.chariot.shadow.item;

import com.chariot.shadow.id.Id;
import com.chariot.shadow.parent.Parent;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Trung Vu on 2017/06/26.
 */
public class UpdateItemTest {
    
    @Tested private UpdateItem updateItem;
    @Injectable private IndexType indexType;
    @Injectable private Id id;
    @Injectable private Map<String, Object> source;
    @Injectable private Parent parent;
    
    @Test
    public void getIdAsString() throws Exception {
        new Expectations() {{
            id.getId(); result = "ID";
        }};
        
        assertThat(updateItem.id(), Is.is("ID"));
    }
}