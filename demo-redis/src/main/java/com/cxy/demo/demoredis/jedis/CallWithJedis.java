package com.cxy.demo.demoredis.jedis;

import redis.clients.jedis.Jedis;

public interface CallWithJedis {
    public void call(Jedis jedis);
}
