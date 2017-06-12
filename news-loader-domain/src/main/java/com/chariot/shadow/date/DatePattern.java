package com.chariot.shadow.date;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Trung Vu on 2017/05/25.
 */
@AllArgsConstructor
@Getter
public enum DatePattern {

    YYYY_MM_DD("yyyyMMdd");

    private String pattern;

    public static DateFormat generateFormatter(DatePattern pattern) {
        return new SimpleDateFormat(pattern.getPattern());
    }
}
