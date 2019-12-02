package com.cxy.demo.demoredis.shortUrl;


import com.cxy.demo.demoredis.redis.shortUrl.RedisStorage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestShortUrl {

    @Autowired
    private RedisStorage redisStorage;

    @Test
    public void testForRedisVersion(){

        String shortUrl = redisStorage.execute("https://www.baidu.com/");
        System.out.println(shortUrl==null?"":shortUrl);
    }


}
