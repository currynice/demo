package com.cxy.demo.demoredis.jedis;



public class JedisTest {
    public static void main(String args[]){
        RedisPool pool = new RedisPool();
        ResultHolder<Long> result = new ResultHolder<>();
        pool.execute(jedis -> {
          long  count = jedis.zcard("name");
            result.setResulr(count);
        });
        System.out.println(result.getResulr());
    }
}
