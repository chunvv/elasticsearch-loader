package com.chariot.shadow.country;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * Created by Trung Vu on 2017/06/26.
 */
@AllArgsConstructor
@Getter
public enum Country {

    JAPAN(81, "JPN"),
    VIETNAM(84, "VNM"),
    SINGAPORE(65, "SGP"),
    MALAYSIA(60, "MYS");

    private int countryCode;
    private String isoCode;

    public static Country get(int countryCode) {
        return Stream.of(values()).filter(country -> country.countryCode == countryCode).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public static Country get(String isoCode) {
        return Stream.of(values()).filter(country -> country.isoCode.equals(isoCode)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
