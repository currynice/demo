package com.cxy.demoaop;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j(topic = "Logger")
public class DemoAopApplicationTests {


   @Resource(name = "testTemplate")
   private RedisTemplate testTemplate;





    @Test
    public void contextLoads() {
    }

    /**
     * 测试Redis序列化器
     *
     * GenericJackson2JsonRedisSerializer、Jackson2JsonRedisSerializer是先将对象转为json，然后再保存到redis，所以，1在redis中是字符串1，所以无法进行加1
     *JdkSerializationRedisSerializer使用的jdk对象序列化，序列化后的值有类信息、版本号等，所以是一个包含很多字母的字符串，所以根本无法加1,
         这个序列化器跟memcache的序列化规则很像memcache怎样存储的对象
     *GenericToStringSerializer、StringRedisSerializer将字符串的值直接转为字节数组，所以保存到redis中是数字，所以可以进行加1
     *
     */
    @Test
    public void testSerivaliar() {
        log.info("");
        testTemplate.setKeySerializer(new StringRedisSerializer());
        testTemplate.setValueSerializer(new StringRedisSerializer());
        testTemplate.opsForValue().set("StringRedisSerializer", "1");

        testTemplate.setKeySerializer(new GenericToStringSerializer<String>(String.class));
        testTemplate.setValueSerializer(new GenericToStringSerializer<String>(String.class));
        testTemplate.opsForValue().set("GenericToStringSerializer", "1");


        testTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        testTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        testTemplate.opsForValue().set("GenericJackson2JsonRedisSerializer", "1");

        testTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<String>(String.class));
        testTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<String>(String.class));
        testTemplate.opsForValue().set("Jackson2JsonRedisSerializer", "1");

        testTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
        testTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        testTemplate.opsForValue().set("JdkSerializationRedisSerializer", "1");

    }

}
