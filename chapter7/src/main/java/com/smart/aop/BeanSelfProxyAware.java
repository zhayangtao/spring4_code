package com.smart.aop;

/**
 * Created by shliangyan on 2017/9/28.
 * 织入自身代理类接口
 */
public interface BeanSelfProxyAware {
    void setSelfProxy(Object object);
}
