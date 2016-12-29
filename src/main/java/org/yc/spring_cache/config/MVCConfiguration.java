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
 *         ��Spring��Java�����У�ͨ��������������һ��Bean�������ڵ��ڵ��ø÷����������������ÿ�ε���schoolBean
 *         ()�������õ���Bean��һ���µ�ʵ����
 *         ͨ��ʹ��@Beanע���עschoolBean()���������֪Spring����ϣ���÷��������BeanҪ��ע���Spring��Ӧ����������
 *         ��
 *         ��ˣ�����Bean�����������������������ʱ��Spring�������ظ÷����ĵ��ã���������Ӧ���������в��Ҹ�Bean���������÷�������һ���µ�ʵ��
 *         �� ���з�ʽ��ApplicationContext context = new
 *         AnnotationConfigApplicationContext(SpringConfig.class);
 */
@Configuration
// ���ڱ�ʾ�������һ�������࣬��������Spring�������Ϣ
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
	    * ���� : <ע����Ϣ��Դ������>. <br>  
	    *<p>  
	        <ʹ�÷���˵��>   
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
	    * ���� : <ע��servlet������>. <br>  
	    *<p>  
	        <ֻ��Ҫ���Զ����servlet����@Controller("ӳ��·��")��ע����>   
	     </p>                                                                                                                                                                                                                                                 
	    * @return                                                                                                       
	    */    
	    @Bean  
	    public HandlerAdapter servletHandlerAdapter(){  
	        log.info("HandlerAdapter");  
	        return new SimpleServletHandlerAdapter();  
	    }  
	      
	    /**                                                           
	    * ���� : <���ػ�������>. <br>  
	    *<p>  
	        <ʹ�÷���˵��>   
	     </p>                                                                                                                                                                                                                                                 
	    * @return                                                                                                       
	    */    
	    @Bean  
	    public LocaleChangeInterceptor localeChangeInterceptor(){  
	        log.info("LocaleChangeInterceptor");  
	        return new LocaleChangeInterceptor();  
	    }  
	      
	    /**                                                           
	    * ���� : <����cookie�ı��ػ���Դ������>. <br>  
	    *<p>  
	        <ʹ�÷���˵��>   
	     </p>                                                                                                                                                                                                                                                 
	    * @return                                                                                                       
	    */    
	    @Bean(name="localeResolver")  
	    public CookieLocaleResolver cookieLocaleResolver(){  
	        log.info("CookieLocaleResolver");  
	        return new CookieLocaleResolver();  
	    }  
	      
	    /**                                                           
	    * ���� : <ע���Զ���������>. <br>  
	    *<p>  
	        <ʹ�÷���˵��>   
	     </p>                                                                                                                                                                                                                                                 
	    * @return                                                                                                       
	    */    
	    /*@Bean  
	    public CP_InitializingInterceptor initializingInterceptor(){  
	        log.info("CP_InitializingInterceptor");  
	        return new CP_InitializingInterceptor();  
	    }  */
	      
	      
	    /**                                                           
	     * ���� : <RequestMappingHandlerMapping��Ҫ��ʾ������������ע���Զ����������>. <br>  
	     *<p>  
	        <����Ƚ����,������Ӧ���ǲ���Ҫ��>   
	      </p>                                                                                                                                                                                                                                                 
	     * @return                                                                                                       
	     */   
	    @Bean  
	    public RequestMappingHandlerMapping requestMappingHandlerMapping() {  
	        log.info("RequestMappingHandlerMapping");  
	        return super.requestMappingHandlerMapping();  
	    }  
	      
	      
	      
	      
	      
	  
	    /**                                                           
	    * ���� : <���������>. <br>  
	    *<p>  
	        <ʹ�÷���˵��>   
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
	     * ���� : <HandlerMapping��Ҫ��ʾ������������ע����Դ���ʴ�����>. <br>  
	     *<p>  
	        <����Ƚ����,������Ӧ���ǲ���Ҫ��>   
	      </p>                                                                                                                                                                                                                                                 
	     * @return                                                                                                       
	     */   
	    @Bean  
	    public HandlerMapping resourceHandlerMapping() {  
	        log.info("HandlerMapping");  
	        return super.resourceHandlerMapping();  
	    }  
	      
	    /**                                                           
	     * ���� : <��Դ���ʴ�����>. <br>  
	     *<p>  
	        <������jsp��ʹ��/static/**�ķ�ʽ����/WEB-INF/static/�µ�����>   
	      </p>                                                                                                                                                                                                                                                 
	     * @param registry                                                                                                       
	     */    
	    @Override  
	    protected void addResourceHandlers(ResourceHandlerRegistry registry) {  
	        log.info("addResourceHandlers");  
	        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");  
	    }  
	      
	    /**                                                            
	    * ���� : <�ļ��ϴ�������>. <br>   
	    *<p>   
	        <ʹ�÷���˵��>    
	     </p>                                                                                                                                                                                                                                                  
	    * @return                                                                                                        
	    */    
	    @Bean(name="multipartResolver")  
	    public CommonsMultipartResolver commonsMultipartResolver(){  
	        log.info("CommonsMultipartResolver");  
	        return new CommonsMultipartResolver();  
	    }  
	      
	    /**                                                           
	    * ���� : <�쳣������>. <br>  
	    *<p>  
	        <ϵͳ����ʱ����ָ�����쳣������ת��ָ����ҳ��>   
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
	     * ���� : <RequestMappingHandlerAdapter��Ҫ��ʾ������������ע��ͨ�����Ա༭��>. <br>  
	     *<p>  
	        <����Ƚ����,������Ӧ���ǲ���Ҫ��>   
	      </p>                                                                                                                                                                                                                                                 
	     * @return                                                                                                       
	     */   
	    @Bean  
	    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {  
	        log.info("RequestMappingHandlerAdapter");  
	        return super.requestMappingHandlerAdapter();  
	    }  
	      
	    /**                                                           
	    * ���� : <ע��ͨ�����Ա༭��>. <br>  
	    *<p>  
	        <����ֻ�������ַ���ת���ں��ַ�������ȥ�ո�Ĵ���>   
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
