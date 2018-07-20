package com.jeesite.modules.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringContextAware
 *
 * Spring的上下文，用来
 * 在线程中获取spring注入的对象
 *
 * @author chf
 * @version 2018-07-12
 */

@Component
public class SpringContextAware implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextAware.applicationContext = applicationContext;
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public static <T> T getBean(String name){
        return (T) applicationContext.getBean(name);
    }

}
