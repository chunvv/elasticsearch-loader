package com.chariot.shadow.item;

import com.chariot.shadow.id.Id;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/06/26.
 */
public class DeleteItemTest {

    @Test
    public void getIndexTypeAsString() throws Exception {
        assertThat(new DeleteItem(IndexType.IMPORT, new Id("1")).getIndexTypeAsString(), Is.is("import"));
    }
}