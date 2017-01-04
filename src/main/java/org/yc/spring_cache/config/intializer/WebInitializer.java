/**
 * @Description:WEB初始化类
 * @author:YC
 * @time:2016年12月29日 下午4:43:57
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
 * @Description:WEB初始化类
 * spring DispatcherServlet的配置,其它servlet和监听器等需要额外声明，用@Order注解设定启动顺序
 * @author:YC
 * @time:2016年12月29日 下午4:43:57
 */
@Order(3)
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	/**
	 * @Description:DispatcherServlet的映射路径
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
	 * @return
	 * return:String[]
	 * @exception:
	 * @author: YC
	 * @time:2016年12月29日 下午4:47:12
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	/**
	 * @Description:应用上下文，除web部分
	 * 加载配置文件类，这里与上面的xml配置是对应的，需要使用@Configuration注解进行标注
	 * @return
	 * return:Class<?>[]
	 * @exception:
	 * @author: YC
	 * @time:2016年12月29日 下午4:47:04
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {DataSourceConfig.class, AppConfig.class};//, SpringSecurityConfig.class
	}

	/* web上下文
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {MVCConfiguration.class};
	}
	
	/* 注册过滤器，映射路径与DispatcherServlet一致，路径不一致的过滤器需要注册到另外的WebApplicationInitializer中
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
