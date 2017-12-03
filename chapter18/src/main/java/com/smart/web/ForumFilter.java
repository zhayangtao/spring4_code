package com.smart.web;

import com.smart.domain.User;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static com.smart.cons.CommonConstant.LOGIN_TO_URL;
import static com.smart.cons.CommonConstant.USER_CONTEXT;

/**
 * Created by shliangyan on 2017/11/8.
 */
public class ForumFilter implements Filter {
    public static final String FILTERED_REQUEST = "@@session_context_filtered_request";
    //不需要登录即可访问的uri资源
    public static final String[] INHERENT_ESCAPE_URIS = {"/index.jsp", "/index.html", "/login.jsp", "/login/doLogin.html", "/register.jsp", "/register.html", "/board/listBoardTopics-", "/board/listTopicPosts-"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest != null && servletRequest.getAttribute(FILTERED_REQUEST) != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //设置过滤标识，防止一次请求多次过滤
            servletRequest.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            User userContext = getSessionUser(httpServletRequest);
            if (userContext == null && !isURILogin((httpServletRequest.getRequestURI()), httpServletRequest)) {
                String toUrl = httpServletRequest.getRequestURL().toString();
                if (StringUtils.isNotEmpty(httpServletRequest.getQueryString())) {
                    toUrl += "?" + httpServletRequest.getQueryString();
                }
                //将用户请求的URL保存在session中，用于登陆后跳转
                httpServletRequest.getSession().setAttribute(LOGIN_TO_URL, toUrl);
                //转发到登录页面
                servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 获取session中的user
     * @param request
     * @return
     */
    protected User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(USER_CONTEXT);
    }

    /**
     * 当前uri资源是否需要登录才能访问
     * @param requestURI
     * @param request
     * @return
     */
    private boolean isURILogin(String requestURI, HttpServletRequest request) {
        if (request.getContextPath().equalsIgnoreCase(requestURI) || (request.getContextPath() + "/").equalsIgnoreCase(requestURI)) {
            return true;
        }
        for (String uri: INHERENT_ESCAPE_URIS) {
            if (requestURI != null && requestURI.indexOf(uri) >= 0) {
                return true;
            }
        }
        return false;
    }

}
