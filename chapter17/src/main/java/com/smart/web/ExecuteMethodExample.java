package com.smart.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.AsyncClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRequestCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by shliangyan on 2017/11/1.
 */
public class ExecuteMethodExample {
    public static void main(String[] args) {
        AsyncRestTemplate template = new AsyncRestTemplate();
        String url = "http://baidu.com";
        HttpMethod method = HttpMethod.GET;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        AsyncRequestCallback requestCallback = new AsyncRequestCallback() {
            @Override
            public void doWithRequest(AsyncClientHttpRequest asyncClientHttpRequest) throws IOException {
                System.out.println(asyncClientHttpRequest.getURI());
            }
        };

        ResponseExtractor<String> responseExtractor = new ResponseExtractor<String>() {
            @Override
            public String extractData(ClientHttpResponse clientHttpResponse) throws IOException {
                return clientHttpResponse.getStatusText();
            }
        };

        Map<String, String> variable = new HashMap<>();
        variable.put("q", "Concretepage");
        ListenableFuture<String> future = template.execute(url, method, requestCallback, responseExtractor, variable);

        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
