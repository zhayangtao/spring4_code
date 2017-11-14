package com.smart.web;

import com.smart.domain.User;
import com.smart.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.smart.cons.CommonConstant.LOGIN_TO_URL;
import static com.smart.cons.CommonConstant.USER_CONTEXT;

/**
 * Created by shliangyan on 2017/11/8.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("/doLogin")
    public ModelAndView login(HttpServletRequest request, User user) {
        User dbUser = userService.getUserByUserName(user.getUserName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login.jsp");
        if (dbUser == null) {
            modelAndView.addObject(ERROR_MSG_KEY, "用户不存在");
        } else if (!dbUser.getPassword().equals(user.getPassword())) {
            modelAndView.addObject(ERROR_MSG_KEY, "用户名或密码错误");
        } else {
//            dbUser.setLastIp(request.getRemoteAddr());
//            dbUser.setLastVisit(new Date());
            setSessionUser(request, dbUser);
            String toUrl = (String) request.getSession().getAttribute(LOGIN_TO_URL);
            request.getSession().removeAttribute(LOGIN_TO_URL);
            if (StringUtils.isEmpty(toUrl)) {
                toUrl = "/index.html";
            }
            modelAndView.setViewName("redirect:" + toUrl);
        }
        return modelAndView;
    }

    /**
     * 用户注销
     * @param session
     * @return
     */
    @RequestMapping("doLogout")
    public String logout(HttpSession session) {
        session.removeAttribute(USER_CONTEXT);
        return "/index.jsp";
    }
}
