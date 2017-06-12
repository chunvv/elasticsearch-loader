package com.chariot.shadow;

import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Trung Vu on 2017/06/05.
 */
@Getter
public class NewsLoaderBootstrap {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"news-loader-interface-customer.xml"});
    }
}
