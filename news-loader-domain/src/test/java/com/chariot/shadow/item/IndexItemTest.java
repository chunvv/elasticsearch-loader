package com.chariot.shadow.item;

import com.chariot.shadow.id.Id;
import com.chariot.shadow.parent.Parent;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/06/26.
 */
public class IndexItemTest {

    @Test
    public void getIndexTypeAsString() throws Exception {
        assertThat(new IndexItem(IndexType.IMPORT, new Id("1"), new HashMap<>(), new Parent("parent")).getIndexTypeAsString(), Is.is("import"));
    }

    @Test
    public void genParentAsString() throws Exception {
        assertThat(new IndexItem(IndexType.IMPORT, new Id("1"), new HashMap<>(), new Parent("parent")).getParentAsString(), Is.is("parent"));
    }
}