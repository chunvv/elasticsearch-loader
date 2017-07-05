package com.chariot.shadow.supplier.news;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * Created by Trung Vu on 2017/05/24.
 */
@AllArgsConstructor
@Getter
public enum NewsSupplierType {

    SKY_NEWS(1, "S", "Sky News"),
    IT_NEWS(2, "I", "IT News"),
    BLOOMBERG(3, "B", "Bloomberg");

    private int id;
    private String code;
    private String name;

    public static NewsSupplierType get(int id) {
        return Stream.of(values()).filter(value -> value.id == id).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
