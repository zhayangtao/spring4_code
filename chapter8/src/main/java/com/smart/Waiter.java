package com.smart;

import com.smart.aspectj.anno.NeedTest;

/**
 * Created by shliangyan on 2017/9/28.
 */
public interface Waiter {
    @NeedTest
    void greetTo(String clientName);

    void serveTo(String clientName);
}
