package com.cxy.demo.demoredis.redis.shortUrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class RedisStorage extends Storage {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;


    public RedisStorage() {
        super();
    }

    //重复插入失败
    @Override
    public boolean insert(String shortUrl, String url) {
        Long value = redisTemplate.opsForSet().add(shortUrl,url);
        value = value==null?0L:value;
        return value>0;
    }

    @Override
    public String getUrlSaved(String shortUrl) {
        //弹出但不移除
        return (String)redisTemplate.opsForSet().randomMember(shortUrl);
    }
}
