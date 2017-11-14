package com.smart.service;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.CollectionMetadata;
import org.testng.annotations.BeforeMethod;
import org.unitils.spring.annotation.SpringBean;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by shliangyan on 2017/11/8.
 */
public class ForumServiceTest extends BaseServiceTest{

    @SpringBean("forumService")
    private ForumService forumService;

    @SpringBean("userService")
    private UserService userService;

    @BeforeMethod
    public void init() {
       SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
        Map<String, CollectionMetadata> map = sessionFactory.getAllCollectionMetadata();
        for (String roleName : map.keySet()) {
            sessionFactory.evictCollection(roleName);
        }
    }
}