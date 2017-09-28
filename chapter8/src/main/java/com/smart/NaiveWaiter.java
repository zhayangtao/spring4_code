package com.smart;

/**
 * Created by shliangyan on 2017/9/28.
 */
public class NaiveWaiter implements Waiter {

    public void greetTo(String clientName) {
        System.out.println("NaiveWaiter:greet to " + clientName + "...");
    }

    public void serveTo(String clientName) {
        System.out.println("NaiveWaiter:serving " + clientName + "...");
    }
}
