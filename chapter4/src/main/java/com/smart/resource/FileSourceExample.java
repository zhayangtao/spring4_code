package com.smart.resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by shliangyan on 2017/9/25.
 */
public class FileSourceExample {
    public static void main(String[] args) throws IOException {
        String filePath = "E:\\workspaceForIDEA\\code\\chapter4\\src\\main\\resources\\conf\\file1.txt";
        // 使用系统文件路径方式加载文件
        WritableResource resource1 = new PathResource(filePath);
        // 使用类路径方式加载文件
        Resource resource2 = new ClassPathResource("conf/file1.txt");
        // 使用 WritableResource 接口写资源文件
        OutputStream stream1 = resource1.getOutputStream();
        stream1.write("欢迎光临\n小春论坛!".getBytes());
        stream1.close();

        // 使用resource接口读取资源文件
        InputStream inputStream1 = resource1.getInputStream();
        InputStream inputStream2 = resource2.getInputStream();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        int i;
        while ((i = inputStream1.read()) != -1) {
            stream.write(i);
        }
        System.out.println(stream.toString());

        System.out.println("resource1:" + resource1.getFilename());
        System.out.println("resource2:" + resource2.getFilename());
    }
}
