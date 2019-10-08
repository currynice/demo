package com.cxy.demo.demoredis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisPool {
    private JedisPool pool;

    public RedisPool() {
        this.pool = new JedisPool();
    }

    public void execute(CallWithJedis caller) {
        try (Jedis jedis = pool.getResource()) {
            try {
                caller.call(jedis);
            }catch (JedisConnectionException e){
                caller.call(jedis);//重连
            }finally {
                jedis.close();
            }

        }
    }
}
