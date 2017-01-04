/**
 * @Description:session监听器
 * @author:YC
 * @time:2016年12月29日 下午5:08:10
 */
package org.yc.spring_cache.config.intializer;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @Description:session监听器
 * @author:YC
 * @time:2016年12月29日 下午5:08:10
 */
@Order(2)
public class WebAppSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	@Override
	protected boolean enableHttpSessionEventPublisher() {
		return true;
	}
}
