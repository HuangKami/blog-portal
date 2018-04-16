package com.kami.blog.redis;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.kami.blog.model.Topic;
import com.kami.blog.service.ArticleService;
import com.kami.blog.util.KeyHelper;


@Service
public class TopicRedis {
	@Autowired
	private RedisTemplate<String, Topic> redisTemplate;
	@Autowired
	private ArticleService articleService;
	
	@PostConstruct
	private void init() {
		redisTemplate.delete(KeyHelper.TOPIC);
		for(Topic topic : articleService.selectTopicDetail()) {
			redisTemplate.opsForSet().add(KeyHelper.TOPIC, topic);
		}
	}
	
	public Set<Topic> getTopics() {
		return redisTemplate.opsForSet().members(KeyHelper.TOPIC);
	}
	
	public void addTopicCount(String topic, int count) {
		Topic topic3 = null;
		for(Topic topic2 : redisTemplate.opsForSet().members(KeyHelper.TOPIC)) {
			if(topic2.getTopic().equals(topic)) {
				topic3 = topic2;
				break;
			}
		}
		if(topic3 != null && topic3.getTotal() + count > 0) {
			redisTemplate.opsForSet().remove(KeyHelper.TOPIC, topic3);
			topic3.setTotal(topic3.getTotal() + count);
			redisTemplate.opsForSet().add(KeyHelper.TOPIC, topic3);
		}
	}
}
