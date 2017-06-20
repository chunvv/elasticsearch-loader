package com.chariot.shadow;

import com.chariot.shadow.news.NewsExecutor;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutionException;

/**
 * Created by Trung Vu on 2017/06/05.
 */
@Getter
public class NewsLoaderBootstrap {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"news-loader-interface-customer.xml"});

        ExecutorType executor = ExecutorType.get(args[0]);
        switch (executor) {
            case NEWS:
                new NewsExecutor().execute();
                break;
            default:
                break;
        }
    }
}
