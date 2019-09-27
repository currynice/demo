package com.cxy.demoaop.redisTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j(topic = "Logger")
public class DifferSerializableWayTests2 {

    //保存一个Prople信息

   @Autowired
   private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    @Qualifier("jdkSerializer")
    private RedisSerializer jdkSerializer;




    @Test
    public void testSerivaliar() throws JsonProcessingException {
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        ObjectMapper om = new ObjectMapper();
//        String jsonPerson = om.writeValueAsString(generateP());
//        redisTemplate.opsForValue().set("StringRedisSerializer", jsonPerson);
//
//
//        redisTemplate.setKeySerializer(new GenericToStringSerializer<String>(String.class));
//        redisTemplate.setValueSerializer(new GenericToStringSerializer<String>(String.class));
//        redisTemplate.opsForValue().set("GenericToStringSerializer", jsonPerson);
//
//
//        redisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.opsForValue().set("GenericJackson2JsonRedisSerializer", generateP());
//
//        redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<String>(String.class));
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<String>(String.class));
//        redisTemplate.opsForValue().set("Jackson2JsonRedisSerializer", generateP());

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        People people = new People("程新宇",20);
        redisTemplate.opsForValue().set("JdkSerializer", people);

        People p  = (People) redisTemplate.opsForValue().get("JdkSerializer");
        System.out.println(p);
    }

    private People generateP(){
        return new People("程新宇",20);
    }




}
