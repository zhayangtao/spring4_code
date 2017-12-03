package com.smart.cache.simplecache;

import com.smart.cache.domain.User;
import com.smart.cache.mycache.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shliangyan on 2017/10/17.
 */
public class UserMain2 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Userservice2 userService = context.getBean("userService2", Userservice2.class);
        System.out.println("first query...");
        userService.getUserById("somebody");
        System.out.println("second query...");
        userService.getUserById("somebody");
    }
}
