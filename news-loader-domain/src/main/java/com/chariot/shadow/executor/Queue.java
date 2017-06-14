package com.chariot.shadow.executor;

import com.chariot.shadow.item.Item;

import java.util.List;

/**
 * Created by Trung Vu on 2017/06/12.
 */
public interface Queue {

    void start();

    void end();
    
    int waitingSize();
    
    void add(Item item);
    
    void put();
    
    void close();
    
    boolean hasExecutable();
    
    List<Item> pollFirst();
}
