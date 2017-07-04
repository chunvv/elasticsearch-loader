package com.chariot.shadow.language;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * Created by Trung Vu on 2017/06/29.
 */
@AllArgsConstructor
@Getter
public enum Language {

    ENGLISH("ENG", "ENGLISH"),
    VIETNAMESE("VNM", "VIETNAMESE"),
    JAPANESE("JPN", "JAPANESE");

    private String code;
    private String name;

    public static Language get(String code) {
        return Stream.of(values()).filter(language -> language.code.equals(code)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
