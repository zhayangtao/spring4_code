package com.smart.aspectj.anno;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by shliangyan on 2017/9/28.
 * 访问注解中的注解
 */
public class ToolTest {
    @Test
    public void tool() {
        //得到ForumService对应的class对象
        Class clazz = ForumService.class;
        //得到ForumService对应的method数组
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println(methods.length);
        for (Method method : methods) {
            //获取方法上标注的注解对象
            NeedTest needTest = method.getAnnotation(NeedTest.class);
            if (needTest != null) {
                if (needTest.value()) {
                    System.out.println(method.getName() + "{}需要测试");
                } else {
                    System.out.println(method.getName() + "{}不需要测试");
                }
            }
        }
    }
}
