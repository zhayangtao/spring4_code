package com.smart.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by shliangyan on 2017/9/27.
 * 自定义后置增强
 */
public class GreetingAfterAdvice implements AfterReturningAdvice {
    /**
     *
     * @param o 目标实例方法返回的结果
     * @param method 目标类的方法
     * @param objects 目标实例方法的入参
     * @param o1 目标类实例
     * @throws Throwable
     */
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("Please enjoy yourself!");
    }
}
