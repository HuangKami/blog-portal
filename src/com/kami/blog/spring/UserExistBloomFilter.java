package com.kami.blog.spring;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.kami.blog.model.User;
import com.kami.blog.service.UserService;

/**
 * spring容器加载完后初始化布隆过滤器
 */
@Component
public class UserExistBloomFilter {
	@Autowired
	private UserService userService;
	public static final BloomFilter<String> nameFilter = 
			BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF-8")), 2 << 12, 0.0001); 
	public static final BloomFilter<String> emailFilter = 
			BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF-8")), 2 << 12, 0.0001);
	
	@PostConstruct
	public void init() {
		for(User user : userService.selectUser(null)) {
			nameFilter.put(user.getName());
			emailFilter.put(user.getEmail());
		}
	}

}
