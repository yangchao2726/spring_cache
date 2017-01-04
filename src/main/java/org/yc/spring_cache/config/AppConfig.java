package org.yc.spring_cache.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

/**
 * @author YC
 * @see http://hanqunfeng.iteye.com/blog/2114975 受益匪浅 打个广告 激活自动代理功能
 */
@Configuration
@ComponentScan(basePackages = "org.yc.spring_cache", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import(value = { DataSourceConfig.class, DaoConfig.class, CachingConfig.class })
public class AppConfig {
}