package com.smart.advisor;

import com.smart.aop.BeanSelfProxyAware;

/**
 * Created by shliangyan on 2017/9/27.
 */
public class Waiter implements BeanSelfProxyAware{
    private Waiter waiter;

    @Override
    public void setSelfProxy(Object object) {
        waiter = (Waiter) object;
    }

    public void greetTo(String name) {
        System.out.println("waiter greet to " + name + "...");
        greetTo(name);
    }

    public void serveTo(String name) {
        System.out.println(name);
    }

}
