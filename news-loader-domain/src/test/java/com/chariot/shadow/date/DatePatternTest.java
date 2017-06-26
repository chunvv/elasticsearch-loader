package com.chariot.shadow.date;

import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Trung Vu on 2017/06/26.
 */
public class DatePatternTest {

    @Test
    public void generateFormatterByPattern() throws Exception {
        assertThat(DatePattern.generateFormatter(DatePattern.YYYY_MM_DD), is(new SimpleDateFormat("yyyyMMdd")));
    }
}