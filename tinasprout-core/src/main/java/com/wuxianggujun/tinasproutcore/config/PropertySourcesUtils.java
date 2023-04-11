package com.wuxianggujun.tinasproutcore.config;

import org.springframework.core.env.*;

import javax.lang.model.element.Name;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author WuXiangGuJun
 * @create 2023-04-11 15:00
 **/
public class PropertySourcesUtils {

    /**
     * 获取属性 {@link Properties}
     *
     * @param propertySources {@link PropertySources}
     * @param prefix          属性前缀
     * @return 属性视图
     * @see Properties
     */
    public static Map<String, Object> getPrefixedProperties(PropertySources propertySources, String prefix) {
        PropertyResolver propertyResolver = new PropertySourcesPropertyResolver(propertySources);
        Map<String, Object> prefixedProperties = new LinkedHashMap<>();
        String normalizedPrefix = buildPrefix(prefix);
        for (PropertySource<?> source : propertySources) {
            if (source instanceof EnumerablePropertySource) {
                for (String name : ((EnumerablePropertySource<?>) source).getPropertyNames()) {
                    if (!prefixedProperties.containsKey(name) && name.startsWith(normalizedPrefix)) {
                        String subName = name.substring(normalizedPrefix.length());
                        if (!prefixedProperties.containsKey(subName)) {
                            Object value = source.getProperty(name);
                            if (value instanceof String) {
                                value = propertyResolver.resolvePlaceholders((String) value);
                            }
                            prefixedProperties.put(subName, value);
                        }
                    }
                }
            }
        }
        return Collections.unmodifiableMap(prefixedProperties);
    }

    /**
     * 构造前缀
     *
     * @param prefix 前缀
     * @return 前缀
     */
    private static String buildPrefix(String prefix) {
        return prefix.endsWith(".") ? prefix : (prefix + ".");
    }


}
