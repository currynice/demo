package com.cxy.performancecounter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

@Configuration
public class RedisOperations {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;





    @Bean
    public ZSetOperations<String,Object> zSetOperations(){
        return redisTemplate.opsForZSet();
    }
}
