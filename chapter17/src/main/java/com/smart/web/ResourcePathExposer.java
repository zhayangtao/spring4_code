package com.smart.web;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Created by zhayangtao on 2017/11/3.
 */
@Service("pathExposer")
public class ResourcePathExposer implements ServletContextAware{
    private ServletContext servletContext;

    private String resourceRoot;

    public void init() {
        String version = "1.2.1";
        resourceRoot = "/resources-" + version;

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String getResourceRoot() {
        return resourceRoot;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }
}
