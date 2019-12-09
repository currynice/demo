package com.cxy.demo.demoredis.redis.repository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisRepositoryServiceTest {

    @Autowired
    private RedisRepositoryService redisRepositoryService;

    @Test
    public void findByName() {
        System.out.println(redisRepositoryService.findByName("wang"));
        System.out.println(redisRepositoryService.findByName("xxx"));
        System.out.println("xxxxxxxxxxxxx");
        System.out.println(redisRepositoryService.findByName("wang"));
        System.out.println(redisRepositoryService.findByName("dai"));
    }
}