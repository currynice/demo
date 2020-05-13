package com.cxy.demo.demoredis.redis.shortUrl;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class RedisStorage extends Storage {

    private static final String SHORT_URLS = "SHORT_URLS";

    @Autowired
    RedisTemplate<String,Object> redisTemplate;


    public RedisStorage() {
        super();
    }

    //重复插入失败
    @Override
    public boolean insert(String shortUrl, String url) {
        return  redisTemplate.opsForHash().putIfAbsent(SHORT_URLS,shortUrl,url);
    }

    @Override
    public String getUrlSaved(String shortUrl) {
        //弹出但不移除
        return (String)redisTemplate.opsForHash().get(SHORT_URLS,shortUrl);
    }
}
