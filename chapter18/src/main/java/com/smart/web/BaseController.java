package com.smart.web;

import com.smart.cons.CommonConstant;
import com.smart.domain.User;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shliangyan on 2017/11/8.
 */
public class BaseController {
    public static final String  ERROR_MSG_KEY = "errorMsg";

    /**
     * 获取保存在session中的用户对象
     * @param request
     * @return
     */
    protected User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
    }

    /**
     * 保存用户对象到session中
     * @param request
     * @param user
     */
    protected void setSessionUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(CommonConstant.USER_CONTEXT, user);
    }

    /**
     * 获取基于应用程序的url绝对路径
     * @param request
     * @param url
     * @return
     */
    public final String getAppBaseUrl(HttpServletRequest request, String url) {
        Assert.hasLength(url, "url不能为空");
        Assert.isTrue(url.startsWith("/"), "必须以/开头");
        return request.getContextPath() + url;
    }
}
