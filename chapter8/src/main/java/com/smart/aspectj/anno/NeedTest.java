package com.smart.aspectj.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by shliangyan on 2017/9/28.
 * 自定义注解类：如果注解只有一个成员，则成员名必须取名value()
 */
@Retention(RetentionPolicy.RUNTIME)//声明注解的保留期限
@Target(ElementType.METHOD)//声明可以使用该注解的目标类型
public @interface NeedTest {
    boolean value() default true;//声明注解成员
}
