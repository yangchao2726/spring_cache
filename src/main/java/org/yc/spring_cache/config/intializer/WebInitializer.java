/**
 * @Description:WEB��ʼ����
 * @author:YC
 * @time:2016��12��29�� ����4:43:57
 */
package org.yc.spring_cache.config.intializer;

import javax.servlet.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.yc.spring_cache.config.AppConfig;
import org.yc.spring_cache.config.DataSourceConfig;
import org.yc.spring_cache.config.MVCConfiguration;

/**
 * @Description:WEB��ʼ����
 * spring DispatcherServlet������,����servlet�ͼ���������Ҫ������������@Orderע���趨����˳��
 * @author:YC
 * @time:2016��12��29�� ����4:43:57
 */
@Order(3)
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	/**
	 * @Description:DispatcherServlet��ӳ��·��
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
	 * @return
	 * return:String[]
	 * @exception:
	 * @author: YC
	 * @time:2016��12��29�� ����4:47:12
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	/**
	 * @Description:Ӧ�������ģ���web����
	 * ���������ļ��࣬�����������xml�����Ƕ�Ӧ�ģ���Ҫʹ��@Configurationע����б�ע
	 * @return
	 * return:Class<?>[]
	 * @exception:
	 * @author: YC
	 * @time:2016��12��29�� ����4:47:04
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {DataSourceConfig.class, AppConfig.class};//, SpringSecurityConfig.class
	}

	/* web������
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {MVCConfiguration.class};
	}
	
	/* ע���������ӳ��·����DispatcherServletһ�£�·����һ�µĹ�������Ҫע�ᵽ�����WebApplicationInitializer��
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletFilters()
	 */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();  
        characterEncodingFilter.setEncoding("UTF-8");  
        characterEncodingFilter.setForceEncoding(true);  
        return new Filter[] {characterEncodingFilter};
	}

}
