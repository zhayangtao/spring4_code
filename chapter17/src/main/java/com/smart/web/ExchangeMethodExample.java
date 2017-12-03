package com.smart.web;

import org.springframework.http.*;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.ExecutionException;

/**
 * Created by shliangyan on 2017/11/1.
 */
public class ExchangeMethodExample {
    public static void main(String[] args) {
        AsyncRestTemplate template = new AsyncRestTemplate();
        String url = "http://baidu.com";
        HttpMethod method = HttpMethod.GET;
        Class<String> responseType = String.class;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        HttpEntity<String> entity = new HttpEntity<String>("params", headers);
        ListenableFuture<ResponseEntity<String>> future = template.exchange(url, method, entity, responseType);

        try {
            ResponseEntity<String> responseEntity = future.get();
            System.out.println(responseEntity.getBody());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
