package com.smart.web;

import com.smart.domain.User;
import com.smart.exception.UserExistException;
import com.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shliangyan on 2017/11/8.
 */
@Controller
public class RegisterController extends BaseController {

    @Autowired
    private UserService userService;

    public ModelAndView register(HttpServletRequest request, User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/success");
        try {
            userService.register(user);
        } catch (UserExistException e) {
            modelAndView.addObject(ERROR_MSG_KEY, "用户已存在");
            modelAndView.setViewName("/register.jsp");
        }
        setSessionUser(request, user);
        return modelAndView;
    }
}
