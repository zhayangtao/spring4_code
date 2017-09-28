package com.smart.aop;

import com.smart.advisor.Waiter;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shliangyan on 2017/9/28.
 */
public class AopAwareTest {
    @Test
    public void autoProxy() {
        String configPath = "com.smart.autoproxy/beans-aware.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = context.getBean("waiter", Waiter.class);
        waiter.serveTo("John");
        waiter.greetTo("John");
    }
}
