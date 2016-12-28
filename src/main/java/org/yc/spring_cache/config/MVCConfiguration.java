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
 * ��Spring��Java�����У�ͨ��������������һ��Bean�������ڵ��ڵ��ø÷����������������ÿ�ε���schoolBean()�������õ���Bean��һ���µ�ʵ����
 * ͨ��ʹ��@Beanע���עschoolBean()���������֪Spring����ϣ���÷��������BeanҪ��ע���Spring��Ӧ���������С�
 * ��ˣ�����Bean�����������������������ʱ��Spring�������ظ÷����ĵ��ã���������Ӧ���������в��Ҹ�Bean���������÷�������һ���µ�ʵ����
 * ���з�ʽ��ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
 */
@Configuration //���ڱ�ʾ�������һ�������࣬��������Spring�������Ϣ
@ComponentScan(basePackages="org.yc.spring_cache")
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass=true) // ���������Զ���������AOP
@Import(value = { TransactionManagementConfig.class,CachingConfig.class }) // ����ָ���������࣬����������Spring���������������Դ����������
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
