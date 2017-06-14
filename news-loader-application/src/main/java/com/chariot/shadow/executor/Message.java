package com.chariot.shadow.executor;

import lombok.Value;

import java.util.List;

/**
 * Created by Trung Vu on 2017/06/14.
 */
@Value
public class Message {

    private int size;
    private List<String> successes;
    private List<String> errors;
}
