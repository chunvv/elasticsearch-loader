package com.chariot.shadow;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/06/26.
 */
public class ExecutorTypeTest {

    @Test
    public void getExecutorByName() throws Exception {
        assertThat(ExecutorType.get("news"), Is.is(ExecutorType.NEWS));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenGetByInvalidName() throws Exception {
        ExecutorType.get("invalid");
    }
}