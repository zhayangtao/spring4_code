package com.smart.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Created by shliangyan on 2017/9/27.
 * 异常增强
 * ThrowsAdvice为标签借口，没有定义任何方法，在运行前spring使用反射机制自行判断，
 * 必须采用以下签名形式定义异常抛出的增强方法
 * void afterThrowing([Method method, Object[] args, Object target], Throwable);
 */
public class GreetingThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
        System.out.println("------------");
        System.out.println("method:" + method.getName());
        System.out.println("抛出异常：" + ex.getMessage());
    }
}
