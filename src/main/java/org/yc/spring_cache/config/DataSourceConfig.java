package org.yc.spring_cache.config;

import java.net.Inet4Address;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

@Configuration
@PropertySources({ @PropertySource("classpath:config.properties"),
		@PropertySource(value = "classpath:param.properties", ignoreResourceNotFound = true)})// 加载指定的配置文件，配置文件内容会加载入Environment中等待调用,param.properties可忽略其FileNotFoundException
public class DataSourceConfig {
	
	@Autowired
	private Environment env;

	private Log log = LogFactory.getLog(DataSourceConfig.class);
	@Bean(name="dataSource")
	public DataSource dataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		String password = "";
		try{
			String ip = Inet4Address.getLocalHost().toString();
			if(-1 != ip.indexOf("192.168.1.16")) password = env.getProperty("jdbc.password.company");
			else password = env.getProperty("jdbc.password.home");
		}catch(Exception e) {
			log.error("数据库密码有误", e);
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
		log.info("加载数据源成功！数据库地址为：" + env.getProperty("jdbc.url"));
		return dataSource;
	}
}