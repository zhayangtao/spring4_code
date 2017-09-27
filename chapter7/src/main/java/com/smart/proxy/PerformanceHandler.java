package com.smart.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by shliangyan on 2017/9/27.
 */
public class PerformanceHandler implements InvocationHandler {

    private Object target;

    public PerformanceHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PerformanceMonitor.begin(target.getClass().getName() + "." + method.getName());
        Object object = method.invoke(target, args);
        PerformanceMonitor.end();
        return object;
    }
}
