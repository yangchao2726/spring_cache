/**
 * 
 */
package org.yc.spring_cache.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator ª∫¥Ê≈‰÷√¿‡
 */
@Configuration
@EnableCaching // ∆Ù”√ª∫¥Ê◊¢Ω‚  <cache:annotation-driven/>
public class CachingConfig {

	@Bean
	public CacheManager getCacheManager() {
		SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		Set<ConcurrentMapCache> caches = new HashSet<ConcurrentMapCache>();
		ConcurrentMapCache cacheContext1 = new ConcurrentMapCache("default");
		caches.add(cacheContext1);
		ConcurrentMapCache cacheContext2 = new ConcurrentMapCache("accountCache");
		caches.add(cacheContext2);
		simpleCacheManager.setCaches(caches);
		return simpleCacheManager;
	}

	/*@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("config/ehcache.xml"));
		return ehCacheManagerFactoryBean;
	}

	@Bean
	public CacheManager cacheManager() {
		logger.info("EhCacheCacheManager");
		EhCacheCacheManager cacheManager = new EhCacheCacheManager();
		cacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
		return cacheManager;
	}*/
}
