package com.chariot.shadow;

import java.util.concurrent.ExecutionException;

/**
 * Created by Trung Vu on 2017/06/20.
 */
public interface Executable {

    void execute() throws ExecutionException, InterruptedException;
}
