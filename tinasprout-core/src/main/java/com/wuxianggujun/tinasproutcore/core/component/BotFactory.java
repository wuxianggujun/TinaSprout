package com.wuxianggujun.tinasproutcore.core.component;

import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.injector.ObjectInjector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WuXiangGuJun
 * @create 2023-04-05 9:06
 **/
@Slf4j
public class BotFactory implements ApplicationContextAware, DisposableBean {

    private static final Map<Long, Bot> bots = new HashMap<>();

    private static ConfigurableEnvironment environment;

    private static ConfigurableApplicationContext applicationContext;

    private static final List<HandlerMethod> handlerMethodList = new ArrayList<>();

    private static Map<String, Map<Class<?>, ObjectInjector<?>>> objectInjectorMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (BotFactory.applicationContext == null && applicationContext instanceof ConfigurableApplicationContext) {
            BotFactory.applicationContext = (ConfigurableApplicationContext) applicationContext;
        }
    }

    public static void setEnvironment(ConfigurableEnvironment environment) {
        BotFactory.environment = environment;
    }

    @Override
    public void destroy() throws Exception {
        log.info("Clear ApplicationContext:" + applicationContext);
        applicationContext = null;
    }


    @SuppressWarnings("all")
    public static void initHandlerMethod() {
        Map<String, Object> beans = BotFactory.getApplicationContext().getBeansOfType(Object.class);
        for (Object bean : beans.values()) {
            
        }
    }


    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBeanByClass(Class<T> clazz) {
        if (applicationContext == null) {
            return null;
        }
        return (T) applicationContext.getBean(clazz);
    }

    public static <T> Map<String, T> getBeansByClass(Class<T> tClass) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBeansOfType(tClass);
    }

}
