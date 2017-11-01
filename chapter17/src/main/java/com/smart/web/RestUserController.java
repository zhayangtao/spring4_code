package com.smart.web;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.Callable;

/**
 * Created by shliangyan on 2017/10/31.
 */
@RestController
public class RestUserController {

    @RequestMapping("/api")
    public Callable<String> api() {
        System.out.println("====hello");
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return null;
            }
        };
    }

    public static void main(String[] args) {
        AsyncRestTemplate template = new AsyncRestTemplate();

    }
}
