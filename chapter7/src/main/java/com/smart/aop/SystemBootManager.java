package com.smart.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.OrderComparator;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

/**
 * Created by shliangyan on 2017/9/28.
 * 启动管理器，用于通知Spring触发BeanSelfProxyAwareMounter装配器的时机
 */
public class SystemBootManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<SystemBootAddon> systemBootAddonList = Collections.EMPTY_LIST;
    private boolean hasRunOnce = false;

    //注入所以SystemBootAddon插件
    @Autowired
    public void setSystemBootAddonList(List<SystemBootAddon> systemBootAddonList) {
        Assert.notEmpty(systemBootAddonList);
        OrderComparator.sort(systemBootAddonList);
        this.systemBootAddonList = systemBootAddonList;
    }

    /**
     * 触发所有插件
     * @param event
     */
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!hasRunOnce) {
            for (SystemBootAddon systemBootAddon : systemBootAddonList) {
                systemBootAddon.onReady();
                if (logger.isDebugEnabled()) {
                    logger.debug("执行插件：{}", systemBootAddon.getClass().getCanonicalName());
                }
            }
            hasRunOnce = true;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("已执行过容器启动插件集");
            }
        }
    }
}
