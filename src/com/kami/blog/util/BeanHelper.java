package com.kami.blog.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring ApplicationContext工具类
 */
@Component
public class BeanHelper implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	private BeanHelper() {}
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}
	
	/**
	 * 获取Bean
	 */
	public static Object getBean(String key) {
		return applicationContext.getBean(key);
	}

}
