package com.stockpin.project;

import java.lang.System.Logger;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class StockpinApplicationTests {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Test
	void contextLoads() {
		/*
		 * ValueOperations : String 제공 클래스 
		 * ListOperations : List 제공 클래스 
		 * SetOperations : Set 제공 클래스 
		 * ZSetOperations : Sorted Set 제공 클래스 
		 * HashOperations : Hash 제공 클래스
		 */
		/*
		 * ValueOperations<String, String> valueOperations =
		 * redisTemplate.opsForValue();
		 * 
		 * String key = "name"; valueOperations.set(key, "김대완"); String value =
		 * valueOperations.get(key); assertEquals(value, "김대완");
		 */
	}

}
