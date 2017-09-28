package com.smart.aspectj.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by shliangyan on 2017/9/28.
 */
@Aspect //通过该注解将PreGreetingAspect标志为一个切面
public class PreGreetingAspect {

    @Before("execution(* greetTo(..))") //定义切面和增强类型
    public void beforeGreeting() {
        System.out.println("How are you");
    }
}
