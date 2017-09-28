package com.smart.aspectj.example;

import com.smart.NaiveWaiter;
import com.smart.Waiter;
import com.smart.aspectj.aspectj.PreGreetingAspect;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 * Created by shliangyan on 2017/9/28.
 */
public class AspectJProxyTest {
    @Test
    public void test() {
        Waiter target = new NaiveWaiter();
        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(target);
        factory.addAspect(PreGreetingAspect.class);

        Waiter proxy = factory.getProxy();
        proxy.greetTo("John");
        proxy.serveTo("John");
    }
}
