package com.cxy.demo.demoredis.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

@Configuration
public class OperationsConfig {
    @Autowired
    private RedisTemplate redisTemplate;


    @Bean("valueOperations")
    public ValueOperations valueOperations(){
        return redisTemplate.opsForValue();
    }

    //产生异步队列
    @Bean("listOperations")
    public ListOperations listOperations(){
        return redisTemplate.opsForList();
    }

    //产生延迟队列
    @Bean("zSetOperations")
    public ZSetOperations zSetOperations(){
        return redisTemplate.opsForZSet();
    }
}
