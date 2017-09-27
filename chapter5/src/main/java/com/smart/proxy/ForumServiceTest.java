package com.smart.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Created by shliangyan on 2017/9/27.
 */
public class ForumServiceTest {
    @Test
    public void proxy() {
        ForumService target = new ForumServiceImpl();
        PerformanceHandler handler = new PerformanceHandler(target);
        ForumService proxy = (ForumService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
        proxy.removeForum(10);
        proxy.removeTopic(1012);
    }

    @Test
    public void cglibProxy() {
        CglibProxy proxy = new CglibProxy();
        ForumService forumService = (ForumService) proxy.getProxy(ForumServiceImpl.class);
        forumService.removeTopic(10);
        forumService.removeTopic(1012);
    }
}
