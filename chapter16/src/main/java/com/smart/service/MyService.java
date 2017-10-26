package com.smart.service;

import org.springframework.stereotype.Service;

/**
 * Created by shliangyan on 2017/10/24.
 */
@Service
public class MyService {
    /**
     * 使用spring调用doJob时，此方法可以为static，但不能含有入参
     */
    public void doJob() {
        System.out.println("in MyService.doJob()");
    }
}
