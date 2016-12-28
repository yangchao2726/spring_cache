package org.yc.spring_cache.config;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

/**
 * @author YC
 * @see http://hanqunfeng.iteye.com/blog/2114975 受益匪浅 打个广告
 * 关于getResourcePatternResolver()的调用  在加载成功后spring会做拦截，而不是再次调用该方法加载bean
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass=true)
@Import(value = {DataSourceConfig.class})
public class TransactionManagementConfig {

	private Log log = LogFactory.getLog(TransactionManagementConfig.class);
	
	@javax.annotation.Resource(name="dataSource")
	private DataSource dataSource;
	
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
	@Bean
	public ResourcePatternResolver getResourcePatternResolver() {
		return new PathMatchingResourcePatternResolver();
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
}
