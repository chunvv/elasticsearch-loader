package com.chariot.shadow.executor;

/**
 * Created by Trung Vu on 2017/06/12.
 */
public interface Executor {

    void pause();

    void resume();
    
    boolean isPaused();
}
