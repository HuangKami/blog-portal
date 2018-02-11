package com.kami.blog.interceptor;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.kami.blog.model.Blackip;
import com.kami.blog.service.BlackipService;
import com.kami.blog.util.BeanHelper;
import com.kami.blog.util.IPHelper;

public class BloomInterceptor extends HandlerInterceptorAdapter {
	public static final int DEFAULT_SIZE  = 2 << 24;
	public static final BloomFilter<String> bloomFilter = 
			BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF-8")), DEFAULT_SIZE, 0.0001); 
	
	static {
		BlackipService blackipService = (BlackipService) BeanHelper.getBean("blackipServiceImpl");
		List<Blackip> blackIps = blackipService.selectBlackip();
		for (Blackip blackip : blackIps) {
			bloomFilter.put(blackip.getIp());
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(bloomFilter.mightContain(IPHelper.getIpAddr(request))) {
			response.sendRedirect(request.getSession().getServletContext().getContextPath() + "/black.jsp");
			return false;
		}
		return true;
	}
}
