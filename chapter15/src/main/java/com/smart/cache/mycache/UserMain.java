package com.smart.cache.mycache;

/**
 * Created by shliangyan on 2017/10/17.
 */
public class UserMain {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.getUserById("001001");
        userService.getUserById("001001");
        userService.reload();
        System.out.println("after reload...");
        userService.getUserById("001001");
        userService.getUserById("001001");
    }
}
