/**
 * 
 */
package org.yc.spring_cache.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * �Զ��廷����
 * ����JavaConfig����Spring������£�ʹ��@PropertySourceע�⽫��������ע��Environment�к�
 * Environment����֧�ֽ����ѱ��������ֵ�������ṩ���������ݻ�ȡ���н��������
 * ������Ҫ�������ע���ΪSpring������һ���������������ʹ��@Componentע�⣬����org.springframework.core.env.Environment���޷�ע���
 */

//@Component
public class YCEnvironment {

	@Autowired
	Environment env;
	
	/**
     * ������kiiwow_��ͷ�����ԣ���ֵ����Ҫ����Base64�����ʹ��
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
     * û����������ʱ����Ĭ��ֵ
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
     * ��ֵת��Ϊָ������
     */
    public <T> T getRequiredProperty(String propertyName, Class<T> targetType) {
        return env.getRequiredProperty(propertyName, targetType);
    }
    
    /**
     * ���Բ������򷵻�Ĭ��ֵ
     */
    public <T> T getRequiredProperty(String propertyName, Class<T> targetType, T defaultValue) {
        try {
            return env.getRequiredProperty(propertyName, targetType);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
