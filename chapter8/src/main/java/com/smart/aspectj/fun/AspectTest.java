package com.smart.aspectj.fun;

import com.smart.Waiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by shliangyan on 2017/9/28.
 */
@Aspect
public class AspectTest {

    @AfterReturning("@annotation(com.smart.aspectj.anno.NeedTest)")
    public void needTestFun() {
        System.out.println("needTestFun() executed");
    }

    @After("within(com.smart.*) && execution(* greetTo(..))")
    public void greetToFun() {
        System.out.println("--greetToFun() executed!");
    }

    @Before(" !target(com.smart.NaiveWaiter) && execution(* serveTo(..))")
    public void notServeNaiveWaiter() {
        System.out.println("notServeNaiveWaiter() executed!");
    }

    @Before("com.smart.aspectj.advanced.TestNamePointcut.inPkgGreetTo()")
    public void pkgGreetTo() {
        System.out.println("pkgGreetTo() executed!");
    }

    @Before(" !target(com.smart.NaiveWaiter) && com.smart.aspectj.advanced.TestNamePointcut.inPkgGreetTo())")
    public void pkgGreetToNotNaiveWaiter() {
        System.out.println("pkgGreetToNotNaiveWaiter() executed!");
    }

    @Around("execution(* greetTo(..)) && target(com.smart.NaiveWaiter)")
    public void joinPointAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("joinPointAccess-----------");
        System.out.println("args[0]:" + joinPoint.getArgs()[0]);
        joinPoint.proceed();//执行目标对象方法
        System.out.println("joinPointAccess-----------");
    }

    @Before("target(com.smart.NaiveWaiter) && args(name, num, ..)")
    public void bindJoinPointParams(int num, String name) {
        System.out.println("----bindJoinPointParams()----");
        System.out.println("name:" + name);
        System.out.println("num:" + num);
        System.out.println("---bindjoinPointParams()---");
    }

    @Before("this(waiter)")
    public void bindProxyObj(Waiter waiter) {
        System.out.println("-----bindProxyObj()-----");
        System.out.println(waiter.getClass().getName());
        System.out.println("----bindProxyObj()----");
    }

    public void bindTypeAnnoObject() {

    }
}
