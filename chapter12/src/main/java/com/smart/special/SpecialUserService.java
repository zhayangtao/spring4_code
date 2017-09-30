package com.smart.special;

import org.springframework.stereotype.Service;

/**
 * Created by shliangyan on 2017/9/30.
 */
@Service
public class SpecialUserService {
    private void method1() {
        System.out.println("method1");
    }
    public final void method2() {
        System.out.println("method2");
    }
    public static void method3() {
        System.out.println("method3");
    }
    public void method4() {
        System.out.println("method4");
    }
    public final void method5() {
        System.out.println("in method5");
    }
    protected void method6() {
        System.out.println("in method6");
    }
}
