package com.kami.blog.interceptor;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.kami.blog.model.Blackip;
import com.kami.blog.service.BlackipService;
import com.kami.blog.util.BeanHelper;
import com.kami.blog.util.IPHelper;
import com.kami.blog.util.SessionHelper;
@SuppressWarnings("unchecked")
/**
 * 拦截IP黑名单的访问
 */
public class BloomInterceptor extends HandlerInterceptorAdapter {
	//布隆过滤器数组大小
	public static final int DEFAULT_SIZE  = 2 << 24;
	//时间内最大访问次数
	public static final int TIMES = 2 << 7;
	//过滤器实体
	public static final BloomFilter<String> bloomFilter = 
			BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF-8")), DEFAULT_SIZE, 0.0001); 
	private static BlackipService blackipService = (BlackipService) BeanHelper.getBean("blackipServiceImpl");
	private RedisTemplate<String, Integer> redisTemplate = (RedisTemplate<String, Integer>) BeanHelper.getBean("redisTemplate");
	private Logger logger = Logger.getLogger(BloomInterceptor.class);

	static {
		//加载库里面得IP黑名单
		for (Blackip blackip : blackipService.selectBlackip()) {
			bloomFilter.put(blackip.getIp());
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//请求的IP
		String ip = IPHelper.getIpAddr(request);
		//过滤黑IP
		if(bloomFilter.mightContain(ip)) {
			response.sendRedirect(request.getSession().getServletContext().getContextPath() + "/black.jsp");
			return false;
		}
		
		//访问次数
		int times = 0;
		//过期剩余时间
		int leftTime = 5;
		if(redisTemplate.hasKey(SessionHelper.getSessionId(request))) {
			//获取访问次数
			times = redisTemplate.opsForValue().get(SessionHelper.getSessionId(request));
			//获取过期剩余时间
			leftTime = redisTemplate.boundValueOps(SessionHelper.getSessionId(request)).get();
		}
		//设置值和key的过期时间
		redisTemplate.opsForValue().set(SessionHelper.getSessionId(request), ++ times);
		redisTemplate.expire(SessionHelper.getSessionId(request), leftTime, TimeUnit.SECONDS);
		
		//大于最大访问次数，拉黑
		if(times >= TIMES) {
			try {
				bloomFilter.put(ip);
				blackipService.insertBlackip(new Blackip(ip));
			} catch (Exception e) {
				logger.error("重复插入");
			} 
			response.sendRedirect(request.getSession().getServletContext().getContextPath() + "/black.jsp");
			return false;
		}
		return true;
	}
}
