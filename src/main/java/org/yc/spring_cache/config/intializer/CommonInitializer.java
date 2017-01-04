/**
 * @Description:收益于：http://hanqunfeng.iteye.com/blog/2114967/
 * @author:YC
 * @time:2016年12月29日 下午4:58:35
 */
package org.yc.spring_cache.config.intializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.util.Log4jConfigListener;

/**
 * @Description:加载Log4jConfigListener
 * @author:YC
 * @time:2016年12月29日 下午4:58:35
 */
@Order(1)
public class CommonInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		servletContext.setInitParameter("log4jConfigLocation", "classpath:log4j.properties");
		servletContext.addListener(Log4jConfigListener.class);
	}

}
