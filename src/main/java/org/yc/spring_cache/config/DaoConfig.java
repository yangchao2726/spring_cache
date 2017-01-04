/**
 * @Description:启用注解事务管理，使用CGLib代理
 * @author:YC
 * @time:2016年12月29日 下午5:33:20
 */
package org.yc.spring_cache.config;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:启用注解事务管理，使用CGLib代理  
 * 关于getResourcePatternResolver()的调用  在加载成功后spring会做拦截，而不是再次调用该方法加载bean
 * @author:YC
 * @time:2016年12月29日 下午5:33:20
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@Import(DataSourceConfig.class)
public class DaoConfig {
	
	@javax.annotation.Resource
	private DataSource dataSource;

	private Logger log = Logger.getLogger(AppConfig.class);
	
	/**
	 * @return
	 * 资源属性占位符解析类（如：${jdbc.driver}等；待证实，暂未使用）
	 */
	/*@Bean  
    public static PropertySourcesPlaceholderConfigurer placehodlerConfigurer() {  
		log.info("PropertySourcesPlaceholderConfigurer");  
        return new PropertySourcesPlaceholderConfigurer();  
    }*/
	
	/**
	 * @Description:通配符方式加载多个绝对匹配的Resource
	 * @return
	 * return:ResourcePatternResolver
	 * @exception:
	 * @author: YC
	 * @time:2016年12月28日 下午1:32:35
	 */
	@Bean("resourcePatternResolver")
	public ResourcePatternResolver getResourcePatternResolver() {
		return new PathMatchingResourcePatternResolver();
	}

	/**
	 * @Description:
	 * @see http://www.cnblogs.com/doit8791/p/4690494.html and http://www.cnblogs.com/soltex/archive/2013/12/10/3466697.html
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 * return:SqlSessionFactoryBean
	 * @exception:
	 * @author: YC
	 * @time:2016年12月28日 下午2:10:09
	 */
	@Bean("sqlSessionFactoryBean")
	public SqlSessionFactoryBean getSqlSessionFactoryBean() throws SQLException, IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
//		Resource[] resource = {new ClassPathResource("org/yc/spring_cache/mapping/MUserMapper.xml")};
		Resource[] resource = getResourcePatternResolver().getResources("classpath*:org/yc/spring_cache/mapping/*.xml");
		sqlSessionFactoryBean.setMapperLocations(resource);
		return sqlSessionFactoryBean;
	}
	
    @Bean("mapperScannerConfigurer")
	public MapperScannerConfigurer getMapperScannerConfigurer() throws SQLException {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("org.yc.spring_cache.dao");
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
		return mapperScannerConfigurer;
	}
    
    /**
	 * 事务管理器
	 * 
	 * @throws SQLException
	 */
	@Bean(name="transactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager() throws SQLException {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource);
		log.info("加载DataSourceTransactionManager完成");
		return dataSourceTransactionManager;
	}
}
