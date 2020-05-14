package com.cxy.demo.demoredis.jedis;

import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * Description: 多线程情况下使用 Jedis 对象操作 Redis 会出现各种奇怪的问题<br>
 * Date: 2020/5/14 13:48  <br>
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class OnlyJedisWrong {
    public static void main(String[] args) throws InterruptedException {
        try (Jedis jedis = new Jedis("127.0.0.1", 6379)) {
            Assert.isTrue("OK".equals(jedis.set("a", "1")), "set a = 1 return OK");
            Assert.isTrue("OK".equals(jedis.set("b", "2")), "set b = 2 return OK");
        }
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            jedisPool.close();


        Jedis jedis = new Jedis("127.0.0.1", 6379);
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                String result = jedis.get("a");
                if (!"1".equals(result)) {
                    System.err.println("Expect a to be 1 but found "+ result);
                    return;
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                String result = jedis.get("b");
                if (!"2".equals(result)) {
                    System.err.println("Expect a to be 2 but found "+ result);
                    return;
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(5);
    }
}
