package com.kami.blog.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserSessionRedis {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	/**
	 * 存入
	 */
	public void saveUserSession(String userId, String sessionId) {
		redisTemplate.opsForValue().set(userId, sessionId);
	}
	
	/**
	 * 读取
	 */
	public String readUserSession(String userId) {
		if(!redisTemplate.hasKey(userId)) {
			return "";
		}
		return redisTemplate.opsForValue().get(userId);
	}
	
	/**
	 * 删除
	 */
	public void deleteUserSession(String userId) {
		if(redisTemplate.hasKey(userId)) {
			redisTemplate.delete(userId);
		}
	}
}
