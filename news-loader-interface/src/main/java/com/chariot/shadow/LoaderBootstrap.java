package com.chariot.shadow;

import com.chariot.shadow.company.CompanyExecutor;
import com.chariot.shadow.news.NewsExecutor;
import lombok.Getter;

import java.util.concurrent.ExecutionException;

/**
 * Created by Trung Vu on 2017/06/05.
 */
@Getter
public class LoaderBootstrap {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorType executor = ExecutorType.get(args[0]);
        switch (executor) {
            case NEWS:
                new NewsExecutor().execute();
                break;
            case COMPANY:
                new CompanyExecutor().execute();
                break;
            default:
                break;
        }
    }
}
