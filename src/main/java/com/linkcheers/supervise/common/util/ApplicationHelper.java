package com.linkcheers.supervise.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @author Soya
 */
@Component
public class ApplicationHelper implements ApplicationContextAware {
 
    private static ApplicationContext applicationContext;
 
    public ApplicationHelper() {
    }
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationHelper.applicationContext = applicationContext;
    }
 
    public static Object getBean(String beanName) {
        return applicationContext != null?applicationContext.getBean(beanName):null;
    }
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext  != null?applicationContext.getBean(clazz):null;
    }
}
