package com.chariot.shadow;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Trung Vu on 2017/06/12.
 */
public interface Loadable<T> {

    void init();

    List<Future<T>> submit();

    void run();

    void shutdown();
}
