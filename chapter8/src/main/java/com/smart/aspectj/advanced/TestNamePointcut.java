package com.smart.aspectj.advanced;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by shliangyan on 2017/9/28.
 */
public class TestNamePointcut {

    @Pointcut("within(com.smart.*)")
    private void inPackage() {

    }

    @Pointcut("execution(* greetTo(..))")
    protected void greetTo() {

    }

    @Pointcut("inPackage() && greetTo()")
    public void inPkgGreetTo() {

    }
}
