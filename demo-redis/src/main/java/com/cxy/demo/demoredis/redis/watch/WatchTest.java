package com.cxy.demo.demoredis.redis.watch;

import cn.hutool.core.util.StrUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * watch乐观锁 与 redis事务
 */
public class WatchTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        String userId = "abc";
        String key = keyFor(userId);
        jedis.setnx(key, String.valueOf(5)); // # setnx 做初始化
        System.out.println(doubleAccount(jedis, userId));
        jedis.close();
    }


    //余额加倍
    public static int doubleAccount(Jedis jedis, String userId) {
        String key = keyFor(userId);
        while (true) {
            jedis.watch(key);
            int value = Integer.parseInt(jedis.get(key));
            value *= 2; // 加倍
            Transaction tx = jedis.multi();
            tx.set(key, String.valueOf(value));
            List<Object> res = tx.exec();
            if (res != null) {//jedis事务失败返回null
                break; // 成功了
                 }
        }
        return Integer.parseInt(jedis.get(key)); // 重新获取余额
    }

        public static String keyFor(String userId) {
            return StrUtil.format("account_{}", userId);
        }
}

