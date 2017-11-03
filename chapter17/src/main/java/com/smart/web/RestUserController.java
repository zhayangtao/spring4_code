package com.smart.web;

import com.smart.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;

import javax.validation.Valid;
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
                Thread.sleep(10000);
                return "test";
            }
        };
    }

    public static void main(String[] args) {
        AsyncRestTemplate template = new AsyncRestTemplate();
        ListenableFuture<ResponseEntity<String >> future = template.getForEntity("http://baidu.com", String.class);
        future.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("failure : " + throwable);
            }

            @Override
            public void onSuccess(ResponseEntity<String> stringResponseEntity) {
                System.out.println("get result : " + stringResponseEntity.getBody());
            }
        });
        System.out.println("no result");
    }

    public String handle91(@Valid @ModelAttribute("user")User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        return "register";
    }
}
