package com.chariot.shadow.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

/**
 * Created by Trung Vu on 2017/06/11.
 */
@Component
public class NewsInfrastructure {

    @Autowired
    private EntityManager entityManager;
}
