package com.smart.advice;

import org.junit.Test;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by shliangyan on 2017/9/27.
 */
public class AdviceTest {

    @Test
    public void before() {
        Waiter target = new NaiveWaiter();
        BeforeAdvice advice = new GreetingBeforeAdvice();

        //代理工厂
        ProxyFactory pf = new ProxyFactory();
        //指定对接口进行代理
        pf.setInterfaces(target.getClass().getInterfaces());
        //true时，使用cglib代理，false时使用jdk代理
        pf.setOptimize(true);
        //设置代理目标
        pf.setTarget(target);
        //为目标添加增强
        pf.addAdvice(advice);
        //生成代理实例
        Waiter proxy = (Waiter) pf.getProxy();
        proxy.greetTo("Joo");
        proxy.serveTo("Tom");
    }

    @Test
    public void after() {
        Waiter target = new NaiveWaiter();
        AfterAdvice advice = new GreetingAfterAdvice();

        //代理工厂
        ProxyFactory factory = new ProxyFactory();
        //设置jdk代理
        factory.setInterfaces(target.getClass().getInterfaces());
        //设置代理目标
        factory.setTarget(target);
        //为目标添加增强
        factory.addAdvice(advice);
        //生成代理实例
        Waiter proxy = (Waiter) factory.getProxy();
        proxy.serveTo("Joo");
        proxy.greetTo("Tom");
    }

    /**
     * 测试环绕增强
     */
    @Test
    public void around() {
        Waiter target = new NaiveWaiter();
        GreetingAroundAdvice advice = new GreetingAroundAdvice();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(advice);

        Waiter proxy = (Waiter) factory.getProxy();
        proxy.serveTo("Joo");
        proxy.greetTo("Tom");
    }

    @Test
    public void throwsTest() {
        ForumService service = new ForumService();
        ThrowsAdvice advice = new GreetingThrowsAdvice();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(service);
        factory.addAdvice(advice);

        ForumService proxy = (ForumService) factory.getProxy();
        proxy.removeForum(1);
        try {
            proxy.updateForum();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
