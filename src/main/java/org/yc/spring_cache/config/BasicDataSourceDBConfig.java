package org.yc.spring_cache.config;

import java.io.IOException;
import java.net.Inet4Address;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

/**
 * @author Administrator
 * 
 */
@Configuration
@PropertySources({ @PropertySource("classpath:config.properties"),
		@PropertySource(value = "classpath:param.properties", ignoreResourceNotFound = true) })
@EnableAspectJAutoProxy // ���������Զ���������AOP
@EnableTransactionManagement
public class BasicDataSourceDBConfig {

	@Autowired
	private Environment env;

	private Log log = LogFactory.getLog(BasicDataSourceDBConfig.class);
	
	/**
	 * @Description:ͨ�����ʽ���ض������ƥ���Resource
	 * @return
	 * return:ResourcePatternResolver
	 * @exception:
	 * @author: YC
	 * @time:2016��12��28�� ����1:32:35
	 */
	@Bean
	public ResourcePatternResolver getResourcePatternResolver() {
		return new PathMatchingResourcePatternResolver();
	}

	@Bean
	public DataSource dataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		String password = "";
		try{
			String ip = Inet4Address.getLocalHost().toString();
			if(-1 != ip.indexOf("192.168.1.16")) password = env.getProperty("jdbc.password.company");
			else password = env.getProperty("jdbc.password.home");
		}catch(Exception e) {
			log.error("���ݿ���������", e);
			throw new SQLException();
		}
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(password);
		dataSource.setInitialSize(0);
		dataSource.setMaxActive(20);
		dataSource.setMaxIdle(20);
		dataSource.setMinIdle(0);
		dataSource.setMaxWait(60000);
		dataSource.setValidationQuery(env.getProperty("jdbc.validationQuery"));
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		dataSource.setMinEvictableIdleTimeMillis(25200000);
		dataSource.setRemoveAbandoned(true);
		dataSource.setRemoveAbandonedTimeoutMillis(1800);
		dataSource.setLogAbandoned(true);
		dataSource.setFilters("mergeStat");
		log.info("��������Դ�ɹ������ݿ��ַΪ��" + env.getProperty("jdbc.url"));
		return dataSource;
	}

	/**
	 * ���������
	 * 
	 * @throws SQLException
	 */
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager() throws SQLException {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource());
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
	 * @time:2016��12��28�� ����2:10:09
	 */
	@Bean("sqlSessionFactoryBean")
	public SqlSessionFactoryBean getSqlSessionFactoryBean() throws SQLException, IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
//		Resource[] resource = {new ClassPathResource("org/yc/spring_cache/mapping/MUserMapper.xml")};
		Resource[] resource = getResourcePatternResolver().getResources("classpath*:org/yc/spring_cache/mapping/*.xml");
		sqlSessionFactoryBean.setMapperLocations(resource);
		return sqlSessionFactoryBean;
	}
}
