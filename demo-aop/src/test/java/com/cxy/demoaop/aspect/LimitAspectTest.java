package com.cxy.demoaop.aspect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LimitAspectTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    @SuppressWarnings("unchecked")
    public void tokenBucketTest(){
        RedisScript<Long> redisScript = RedisScript.of(buildLimitQPSScript(),Long.class);
        Number count = (Number) redisTemplate.execute(redisScript, Collections.singletonList("tets"),50,20);
        Number count2 = (Number) redisTemplate.execute(redisScript, Collections.singletonList("tets"),50,20);
        Number count3 = (Number) redisTemplate.execute(redisScript, Collections.singletonList("tets"),50,20);
        System.out.println(count);
        System.out.println(count2);
        System.out.println(count3);
    }




    private String buildLimitQPSScript(){
        //操作的 Redis Key
        return "local rate_limit_key = KEYS[1]"+
                // 每秒最大的 QPS 许可数
                "\nlocal max_permits = ARGV[1]"+
                //此次申请的许可数
                "\nlocal incr_by_count_str = ARGV[2]"+
                //当前已用的许可数
                "\nlocal currentStr = redis.call('get', rate_limit_key)"+
                "\nlocal current = 0"+
                "\nif currentStr then"+
                "\ncurrent = tonumber(currentStr)"+
                "\nend"+
                //-- 剩余可分发的许可数(最大-已用)
                "\nlocal remain_permits = tonumber(max_permits) - current"+
                "\nlocal incr_by_count = tonumber(incr_by_count_str)"+
                //如果可分发的许可数小于申请的许可数，只能申请到可分发的许可数
                "\nif remain_permits < incr_by_count then"+
                "\nincr_by_count = remain_permits"+
                "\nend"+
                //将此次实际申请的许可数加到 Redis Key 里面
                "\nlocal result = redis.call('incrby', rate_limit_key, incr_by_count)"+
                //初次操作 Redis Key 设置 1 秒的过期
                "\nif result == incr_by_count then"+
                "\nredis.call('expire', rate_limit_key, 1)"+
                "\nend"+

                // -- 返回实际申请到的许可数
                "\nreturn incr_by_count";

    }

}