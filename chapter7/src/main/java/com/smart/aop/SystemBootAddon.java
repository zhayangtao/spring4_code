package com.smart.aop;

import org.springframework.core.Ordered;

/**
 * Created by shliangyan on 2017/9/28.
 */
public interface SystemBootAddon extends Ordered {
    void onReady();
}
