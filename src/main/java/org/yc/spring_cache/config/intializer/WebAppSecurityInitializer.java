/**
 * @Description:session������
 * @author:YC
 * @time:2016��12��29�� ����5:08:10
 */
package org.yc.spring_cache.config.intializer;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @Description:session������
 * @author:YC
 * @time:2016��12��29�� ����5:08:10
 */
@Order(2)
public class WebAppSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	@Override
	protected boolean enableHttpSessionEventPublisher() {
		return true;
	}
}
