/**
 * @Description:����ע���������ʹ��CGLib����
 * @author:YC
 * @time:2016��12��29�� ����5:33:20
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
 * @Description:����ע���������ʹ��CGLib����  
 * ����getResourcePatternResolver()�ĵ���  �ڼ��سɹ���spring�������أ��������ٴε��ø÷�������bean
 * @author:YC
 * @time:2016��12��29�� ����5:33:20
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
	 * ��Դ����ռλ�������ࣨ�磺${jdbc.driver}�ȣ���֤ʵ����δʹ�ã�
	 */
	/*@Bean  
    public static PropertySourcesPlaceholderConfigurer placehodlerConfigurer() {  
		log.info("PropertySourcesPlaceholderConfigurer");  
        return new PropertySourcesPlaceholderConfigurer();  
    }*/
	
	/**
	 * @Description:ͨ�����ʽ���ض������ƥ���Resource
	 * @return
	 * return:ResourcePatternResolver
	 * @exception:
	 * @author: YC
	 * @time:2016��12��28�� ����1:32:35
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
	 * @time:2016��12��28�� ����2:10:09
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
	 * ���������
	 * 
	 * @throws SQLException
	 */
	@Bean(name="transactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager() throws SQLException {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource);
		log.info("����DataSourceTransactionManager���");
		return dataSourceTransactionManager;
	}
}
