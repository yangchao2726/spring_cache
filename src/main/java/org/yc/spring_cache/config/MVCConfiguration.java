package org.yc.spring_cache.config;

import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

/**
 * @author Administrator
 *         在Spring的Java配置中，通过声明方法引用一个Bean并不等于等于调用该方法。如果真这样，每次调用schoolBean
 *         ()，都将得到该Bean的一个新的实例。
 *         通过使用@Bean注解标注schoolBean()方法，会告知Spring我们希望该方法定义的Bean要被注册进Spring的应用上下文中
 *         。
 *         因此，其他Bean的声明方法中运用这个方法时，Spring都会拦截该方法的调用，并尝试在应用上下文中查找该Bean，而不是让方法创建一个新的实例
 *         。 运行方式：ApplicationContext context = new
 *         AnnotationConfigApplicationContext(SpringConfig.class);
 */
@Configuration
// 用于表示这个类是一个配置类，用于配置Spring的相关信息
@EnableWebMvc
@ComponentScan(basePackages = "org.yc.spring_cache", useDefaultFilters = false, includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
public class MVCConfiguration extends WebMvcConfigurationSupport {

	private Log log = LogFactory.getLog(MVCConfiguration.class);

	@Bean
	public ViewResolver getViewResolver() {
		log.info("ViewResolver");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	/**                                                           
	    * 描述 : <注册消息资源处理器>. <br>  
	    *<p>  
	        <使用方法说明>   
	     </p>                                                                                                                                                                                                                                                 
	    * @return                                                                                                       
	    */    
	    @Bean  
	    public MessageSource messageSource() {  
	        log.info("MessageSource");  
	        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();  
	        messageSource.setBasename("config.messages.messages");  
	          
	        return messageSource;  
	    }  
	      
	    /**                                                           
	    * 描述 : <注册servlet适配器>. <br>  
	    *<p>  
	        <只需要在自定义的servlet上用@Controller("映射路径")标注即可>   
	     </p>                                                                                                                                                                                                                                                 
	    * @return                                                                                                       
	    */    
	    @Bean  
	    public HandlerAdapter servletHandlerAdapter(){  
	        log.info("HandlerAdapter");  
	        return new SimpleServletHandlerAdapter();  
	    }  
	      
	    /**                                                           
	    * 描述 : <本地化拦截器>. <br>  
	    *<p>  
	        <使用方法说明>   
	     </p>                                                                                                                                                                                                                                                 
	    * @return                                                                                                       
	    */    
	    @Bean  
	    public LocaleChangeInterceptor localeChangeInterceptor(){  
	        log.info("LocaleChangeInterceptor");  
	        return new LocaleChangeInterceptor();  
	    }  
	      
	    /**                                                           
	    * 描述 : <基于cookie的本地化资源处理器>. <br>  
	    *<p>  
	        <使用方法说明>   
	     </p>                                                                                                                                                                                                                                                 
	    * @return                                                                                                       
	    */    
	    @Bean(name="localeResolver")  
	    public CookieLocaleResolver cookieLocaleResolver(){  
	        log.info("CookieLocaleResolver");  
	        return new CookieLocaleResolver();  
	    }  
	      
	    /**                                                           
	    * 描述 : <注册自定义拦截器>. <br>  
	    *<p>  
	        <使用方法说明>   
	     </p>                                                                                                                                                                                                                                                 
	    * @return                                                                                                       
	    */    
	    /*@Bean  
	    public CP_InitializingInterceptor initializingInterceptor(){  
	        log.info("CP_InitializingInterceptor");  
	        return new CP_InitializingInterceptor();  
	    }  */
	      
	      
	    /**                                                           
	     * 描述 : <RequestMappingHandlerMapping需要显示声明，否则不能注册自定义的拦截器>. <br>  
	     *<p>  
	        <这个比较奇怪,理论上应该是不需要的>   
	      </p>                                                                                                                                                                                                                                                 
	     * @return                                                                                                       
	     */   
	    @Bean  
	    public RequestMappingHandlerMapping requestMappingHandlerMapping() {  
	        log.info("RequestMappingHandlerMapping");  
	        return super.requestMappingHandlerMapping();  
	    }  
	      
	      
	      
	      
	      
	  
	    /**                                                           
	    * 描述 : <添加拦截器>. <br>  
	    *<p>  
	        <使用方法说明>   
	     </p>                                                                                                                                                                                                                                                 
	    * @param registry                                                                                                       
	    */    
	   /* @Override  
	    protected void addInterceptors(InterceptorRegistry registry) {  
	        log.info("addInterceptors start");  
	        registry.addInterceptor(localeChangeInterceptor());  
	        registry.addInterceptor(initializingInterceptor());  
	        log.info("addInterceptors end");  
	    }  */
	  
	    /**                                                           
	     * 描述 : <HandlerMapping需要显示声明，否则不能注册资源访问处理器>. <br>  
	     *<p>  
	        <这个比较奇怪,理论上应该是不需要的>   
	      </p>                                                                                                                                                                                                                                                 
	     * @return                                                                                                       
	     */   
	    @Bean  
	    public HandlerMapping resourceHandlerMapping() {  
	        log.info("HandlerMapping");  
	        return super.resourceHandlerMapping();  
	    }  
	      
	    /**                                                           
	     * 描述 : <资源访问处理器>. <br>  
	     *<p>  
	        <可以在jsp中使用/static/**的方式访问/WEB-INF/static/下的内容>   
	      </p>                                                                                                                                                                                                                                                 
	     * @param registry                                                                                                       
	     */    
	    @Override  
	    protected void addResourceHandlers(ResourceHandlerRegistry registry) {  
	        log.info("addResourceHandlers");  
	        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");  
	    }  
	      
	    /**                                                            
	    * 描述 : <文件上传处理器>. <br>   
	    *<p>   
	        <使用方法说明>    
	     </p>                                                                                                                                                                                                                                                  
	    * @return                                                                                                        
	    */    
	    @Bean(name="multipartResolver")  
	    public CommonsMultipartResolver commonsMultipartResolver(){  
	        log.info("CommonsMultipartResolver");  
	        return new CommonsMultipartResolver();  
	    }  
	      
	    /**                                                           
	    * 描述 : <异常处理器>. <br>  
	    *<p>  
	        <系统运行时遇到指定的异常将会跳转到指定的页面>   
	     </p>                                                                                                                                                                                                                                                 
	    * @return                                                                                                       
	    */    
	    /*@Bean(name="exceptionResolver")  
	    public CP_SimpleMappingExceptionResolver simpleMappingExceptionResolver(){  
	        log.info("CP_SimpleMappingExceptionResolver");  
	        CP_SimpleMappingExceptionResolver simpleMappingExceptionResolver= new CP_SimpleMappingExceptionResolver();  
	        simpleMappingExceptionResolver.setDefaultErrorView("common_error");  
	        simpleMappingExceptionResolver.setExceptionAttribute("exception");  
	        Properties properties = new Properties();  
	        properties.setProperty("java.lang.RuntimeException", "common_error");  
	        simpleMappingExceptionResolver.setExceptionMappings(properties);  
	        return simpleMappingExceptionResolver;  
	    }*/  
	      
	     /**                                                           
	     * 描述 : <RequestMappingHandlerAdapter需要显示声明，否则不能注册通用属性编辑器>. <br>  
	     *<p>  
	        <这个比较奇怪,理论上应该是不需要的>   
	      </p>                                                                                                                                                                                                                                                 
	     * @return                                                                                                       
	     */   
	    @Bean  
	    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {  
	        log.info("RequestMappingHandlerAdapter");  
	        return super.requestMappingHandlerAdapter();  
	    }  
	      
	    /**                                                           
	    * 描述 : <注册通用属性编辑器>. <br>  
	    *<p>  
	        <这里只增加了字符串转日期和字符串两边去空格的处理>   
	     </p>                                                                                                                                                                                                                                                 
	    * @return                                                                                                       
	    */    
	    /*@Override  
	    protected ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer() {  
	        log.info("ConfigurableWebBindingInitializer");  
	        ConfigurableWebBindingInitializer initializer = super.getConfigurableWebBindingInitializer();  
	        CP_PropertyEditorRegistrar register = new CP_PropertyEditorRegistrar();  
	        register.setFormat("yyyy-MM-dd");  
	        initializer.setPropertyEditorRegistrar(register);  
	        return initializer;  
	    }*/

}
