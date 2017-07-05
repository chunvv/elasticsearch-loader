package com.chariot.shadow.company;

import com.chariot.shadow.Executable;

import java.util.concurrent.ExecutionException;

/**
 * Created by Trung Vu on 2017/07/06.
 */
public class CompanyExecutor implements Executable {
    
    @Override
    public void execute() throws ExecutionException, InterruptedException {
        new CompanyLoader().run();
    }
}
