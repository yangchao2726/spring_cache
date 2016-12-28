package org.yc.spring_cache.config;

import java.sql.SQLException;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Administrator
 * 在Spring的Java配置中，通过声明方法引用一个Bean并不等于等于调用该方法。如果真这样，每次调用schoolBean()，都将得到该Bean的一个新的实例。
 * 通过使用@Bean注解标注schoolBean()方法，会告知Spring我们希望该方法定义的Bean要被注册进Spring的应用上下文中。
 * 因此，其他Bean的声明方法中运用这个方法时，Spring都会拦截该方法的调用，并尝试在应用上下文中查找该Bean，而不是让方法创建一个新的实例。
 * 运行方式：ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
 */
@Configuration //用于表示这个类是一个配置类，用于配置Spring的相关信息
@ComponentScan(basePackages="org.yc.spring_cache")
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass=true) // 启用切面自动代理，用于AOP
@Import(value = { TransactionManagementConfig.class,CachingConfig.class }) // 引入指定的配置类，我们引入了Spring容器配置类和数据源事务配置类
public class MVCConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
    @Bean
	public MapperScannerConfigurer getMapperScannerConfigurer() throws SQLException {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("org.yc.spring_cache.dao");
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
		return mapperScannerConfigurer;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
}
