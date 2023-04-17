package com.wuxianggujun.tinasproutcore.core.component;

import com.wuxianggujun.tinasproutcore.annotation.*;
import com.wuxianggujun.tinasproutcore.config.BotConfig;
import com.wuxianggujun.tinasproutcore.config.PropertySourcesUtils;
import com.wuxianggujun.tinasproutcore.core.Bot;
import com.wuxianggujun.tinasproutcore.core.network.BotNetworkFactory;
import com.wuxianggujun.tinasproutcore.event.BaseEvent;
import com.wuxianggujun.tinasproutcore.exception.BotException;
import com.wuxianggujun.tinasproutcore.injector.ObjectInjector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author xiaoxu
 * @since 2022-05-24 10:19
 */
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
    public void destroy() {
        log.info("clear ApplicationContext:" + applicationContext);
        applicationContext = null;
    }

    @SuppressWarnings("all")
    public static void initHandlerMethod() {
        Map<String, Object> beans = BotFactory.getApplicationContext().getBeansOfType(Object.class);
        for (Object bean : beans.values()) {
            Class<?> beanClass = ClassUtils.getUserClass(bean);
            Set<Method> methodSet = Arrays.stream(beanClass.getMethods()).filter(method ->
                    method.isAnnotationPresent(GroupMessageHandler.class)
                            || method.isAnnotationPresent(TempMessageHandler.class)
                            || method.isAnnotationPresent(FriendMessageHandler.class)
                            || method.isAnnotationPresent(GroupRecallHandler.class)
                            || method.isAnnotationPresent(MemberAddHandler.class)
                            || method.isAnnotationPresent(AddFriendHandler.class)
            ).collect(Collectors.toSet());
            methodSet.forEach(method -> {
                HandlerMethod handlerMethod = new HandlerMethod() {
                    {
                        setType(beanClass);
                        setMethod(method);
                        setObject(bean);
                    }
                };
                handlerMethodList.add(handlerMethod);
            });
        }
        objectInjectorMap = new HashMap<>();
        Map<String, ObjectInjector> objectInjectors = getBeansByClass(ObjectInjector.class);
        if (objectInjectors != null) {
            for (ObjectInjector objectInjector : objectInjectors.values()) {
                for (String type : objectInjector.getType()) {
                    Map<Class<?>, ObjectInjector<?>> objectInjectorMapTemp = BotFactory.objectInjectorMap.computeIfAbsent(type, key -> new HashMap<>());
                    objectInjectorMapTemp.put(objectInjector.getClassType(), objectInjector);
                }
            }
        }
        log.info("事件处理器初始化完成.");
    }

    public static void initBot() {
        BotDispatcher botDispatcher = BotFactory.getBeanByClass(BotDispatcher.class);
        if (botDispatcher == null) {
            throw new BotException("BotDispatcher初始化失败");
        }
        String configKey = "bot";
        List<BotConfig> botConfigs = null;
        if (PropertySourcesUtils.getPrefixedProperties(BotFactory.environment.getPropertySources(), configKey).size() != 0
                || PropertySourcesUtils.getPrefixedProperties(BotFactory.environment.getPropertySources(), configKey + "[0]").size() != 0) {
            Binder binder = Binder.get(BotFactory.environment);
            if (PropertySourcesUtils.getPrefixedProperties(BotFactory.environment.getPropertySources(), configKey + "[0]").size() > 0) {
                botConfigs = binder.bind(configKey, Bindable.listOf(BotConfig.class)).get();
            } else {
                botConfigs = new ArrayList<>();
                botConfigs.add(binder.bind(configKey, Bindable.of(BotConfig.class)).get());
            }
        }
        if (botConfigs != null && !botConfigs.isEmpty()) {
            BotNetworkFactory.initBotNetwork(botConfigs, bots, botDispatcher);
        }

    }

    public static void addBot(BotConfig botConfig) {
        List<BotConfig> botConfigs = new ArrayList<>();
        botConfigs.add(botConfig);
        BotDispatcher botDispatcher = BotFactory.getBeanByClass(BotDispatcher.class);
        if (botDispatcher == null) {
            throw new BotException("BotDispatcher初始化失败");
        }
        BotNetworkFactory.initBotNetwork(botConfigs, bots, botDispatcher);
    }

    public static void addBot(List<BotConfig> botConfigs) {
        BotDispatcher botDispatcher = BotFactory.getBeanByClass(BotDispatcher.class);
        if (botDispatcher == null) {
            throw new BotException("BotDispatcher初始化失败");
        }
        BotNetworkFactory.initBotNetwork(botConfigs, bots, botDispatcher);
    }

    public static Map<Long, Bot> getBots() {
        return bots;
    }


    /**
     * 获取被注解标注的方法
     *
     * @param predicate 用于筛选出符合要求的HandlerMethod
     * @return Set<HandlerMethod>
     */
    public static Set<HandlerMethod> getHandlerMethodListByAnnotation(Predicate<? super HandlerMethod> predicate) {
        if (handlerMethodList.isEmpty()) {
            return new HashSet<>();
        }
        return handlerMethodList.stream().filter(predicate).collect(Collectors.toSet());
    }


    public static List<Object> handleMethod(Bot bot, BaseEvent event, Predicate<? super HandlerMethod> predicate, String objectInjectorType) {
        List<Object> resultList = new ArrayList<>();
        Set<HandlerMethod> handlerMethodSet = getHandlerMethodListByAnnotation(predicate);
        for (HandlerMethod handlerMethod : handlerMethodSet) {
            //返回一个Class对象数组，这些对象按声明顺序表示此对象表示的可执行文件的形式参数类型。如果底层可执行文件不带参数，则返回长度为 0 的数组
            Class<?>[] parameterTypes = handlerMethod.getMethod().getParameterTypes();
            
            Object[] objects = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                //获取当前参数的类型
                Class<?> parameterType = parameterTypes[i];
                // 从对象注入器映射中获取指定类型的对象注入器
                ObjectInjector<?> objectInjector = objectInjectorMap.get(objectInjectorType) != null ? objectInjectorMap.get(objectInjectorType).get(parameterType) : null;
                if (objectInjector == null) {
                    objectInjector = objectInjectorMap.get("all") != null ? objectInjectorMap.get("all").get(parameterType) : null;
                }
                if (objectInjector == null) {
                    objects[i] = null;
                } else {
                    // 调用对象注入器的 getObject() 方法获取参数对象，并将其存入数组中
                    objects[i] = objectInjector.getObject(event, bot);
                }
            }
            try {
                // 调用处理器方法，传入参数数组，并将结果添加到结果列表中
                resultList.add(handlerMethod.getMethod().invoke(handlerMethod.getObject(), objects));
            } catch (IllegalAccessException | InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause == null) {
                    log.error(e.getMessage(), e);
                } else {
                    log.error(cause.getMessage(), cause);
                }
                return new ArrayList<>();
            }
        }
        return resultList;
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBeanByClass(Class<T> clazz) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(clazz);
    }

    public static <T> Map<String, T> getBeansByClass(Class<T> tClass) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBeansOfType(tClass);
    }
}
