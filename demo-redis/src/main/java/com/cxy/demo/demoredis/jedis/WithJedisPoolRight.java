package com.cxy.demo.demoredis.jedis;

import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

/**
 * Description: 使用JedisPool,获取/归还jedis实例<br>
 * Date: 2020/5/14 13:48  <br>
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class WithJedisPoolRight {

  private static JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);

    public static void main(String[] args) throws InterruptedException {
//        try (Jedis jedis = new Jedis("127.0.0.1", 6379)) {
//            Assert.isTrue("OK".equals(jedis.set("a", "1")), "set a = 1 return OK");
//            Assert.isTrue("OK".equals(jedis.set("b", "2")), "set b = 2 return OK");
//        }
        //add shutdown钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            jedisPool.close();
        }));
        new Thread(() -> {});}}
//            try (Jedis jedis = jedisPool.getResource()) {
//                for (int i = 0; i < 1000; i++) {
//                    String result = jedis.get("a");
//                    if (!"1".equals(result)) {
//                        log.warn("Expect a to be 1 but found {}", result);
//                        return;
//                    }
//                }
//            }
//        }).start();
//        new Thread(() -> {
//            try (Jedis jedis = jedisPool.getResource()) {
//                for (int i = 0; i < 1000; i++) {
//                    String result = jedis.get("b");
//                    if (!"2".equals(result)) {
//                        log.warn("Expect b to be 2 but found {}", result);
//                        return;
//                    }
//                }
//            }
//        }).start();
//        TimeUnit.SECONDS.sleep(5);
