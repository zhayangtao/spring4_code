package com.smart.aspectj.fun;

import com.smart.NaiveWaiter;
import com.smart.NaughtyWaiter;
import com.smart.Waiter;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shliangyan on 2017/9/28.
 */
public class PointcutFunTest {
    @Test
    public void pointcut() {
        String configPath = "beans.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configPath);
        Waiter naiveWaiter = context.getBean("naiveWaiter", NaiveWaiter.class);
        Waiter naughtyWaiter = context.getBean("naughtyWaiter", NaughtyWaiter.class);
        naiveWaiter.greetTo("John"); //未增强
        naughtyWaiter.greetTo("Tom"); //使用增强
    }
}
