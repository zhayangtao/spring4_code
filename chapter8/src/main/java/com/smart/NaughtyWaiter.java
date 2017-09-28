package com.smart;

import com.smart.aspectj.anno.NeedTest;

/**
 * Created by shliangyan on 2017/9/28.
 */
public class NaughtyWaiter implements Waiter {
    @NeedTest
    public void greetTo(String clientName) {
        System.out.println("NaughtWaiter greet to " + clientName);
    }

    public void serveTo(String clientName) {
        System.out.println("naughtWaiter serve to " + clientName);
    }
}
