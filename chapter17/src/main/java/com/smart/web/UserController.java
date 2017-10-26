package com.smart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shliangyan on 2017/10/26.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @RequestMapping("register")
    public String register() {
        return "user/register";
    }
}
