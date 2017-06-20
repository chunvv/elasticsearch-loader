package com.chariot.shadow;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

/**
 * Created by Trung Vu on 2017/06/21.
 */
@AllArgsConstructor
public enum ExecutorType {

    NEWS(1, "news"),
    COMPANY(2, "company");

    private int id;
    private String name;

    public static ExecutorType get(String name) {
        return Stream.of(values()).filter(value -> value.name == name).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
