package com.chariot.shadow.supplier.company;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * Created by Trung Vu on 2017/07/04.
 */
@AllArgsConstructor
@Getter
public enum CompanySupplierType {

    VIETNAM(1, "S", "Vietnam Economy");

    private int id;
    private String code;
    private String name;

    public static CompanySupplierType get(int id) {
        return Stream.of(values()).filter(value -> value.id == id).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
