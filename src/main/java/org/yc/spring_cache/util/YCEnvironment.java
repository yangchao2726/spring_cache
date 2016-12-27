/**
 * 
 */
package org.yc.spring_cache.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * 自定义环境类
 * 基于JavaConfig配置Spring的情况下，使用@PropertySource注解将配置属性注入Environment中后
 * Environment不能支持解码已编码的属性值。所以提供这个类对数据获取进行解码操作。
 * 我们需要将这个类注册成为Spring容器的一个组件，即在类上使用@Component注解，否则org.springframework.core.env.Environment是无法注入的
 */

//@Component
public class YCEnvironment {

	@Autowired
	Environment env;
	
	/**
     * 所有以kiiwow_开头的属性，其值都需要经过Base64解码后使用
     */
    public String getProperty(String propertyName) {
        if (propertyName.startsWith("yc_")) {
            String originalValue = env.getProperty(propertyName);
//            return DigestUtils.decodeBase64(originalValue);
            return null;
        } else {
            return env.getProperty(propertyName);
        }
    }
    
    /**
     * 没有配置属性时采用默认值
     */
    public String getProperty(String propertyName, String defaultValue) {
        if (propertyName.startsWith("yc_")) {
            String originalValue = env.getProperty(propertyName);
//            return originalValue == null ? null : DigestUtils.decodeBase64(originalValue);
            return null;
        } else {
            return env.getProperty(propertyName) == null ? defaultValue : env.getProperty(propertyName);
        }
    }
    
    /**
     * 将值转换为指定类型
     */
    public <T> T getRequiredProperty(String propertyName, Class<T> targetType) {
        return env.getRequiredProperty(propertyName, targetType);
    }
    
    /**
     * 属性不存在则返回默认值
     */
    public <T> T getRequiredProperty(String propertyName, Class<T> targetType, T defaultValue) {
        try {
            return env.getRequiredProperty(propertyName, targetType);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
