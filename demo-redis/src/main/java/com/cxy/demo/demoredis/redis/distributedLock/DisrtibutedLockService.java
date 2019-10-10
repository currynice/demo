package com.cxy.demo.demoredis.redis.distributedLock;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * 仅对 redis 单实例架构有效，当面对 redis 集群时就无效了。
 * 但是一般情况下，通过 redis 哨兵实现主从切换这种模式下,
 * 我们的应用服务器直接面向主机，也可看成是单实例，因此上述代码实现也有效。
 * 但是当在主机宕机，从机被升级为主机的一瞬间的时候
 * 比如:原先第一个客户端在主节点中申请成功了一把锁，
 * 但是这把锁还没有来得及同步到从节点，主节点突然挂掉了。
 * 然后从节点变成了主节点，这个新的节点内部没有这个锁，所以当
 * 另一个客户端过来请求加锁时，立即就批准了。这样就会导致系统中同样一把锁被两个客户
 * 端同时持有，不安全性由此产生。
 * 原因在于 redis 主从复制的异步性，
 * 导致从机中数据没有即时同步，那么上述代码依然会无效，导致同一资源有可能会产生两把锁，违背了分布式锁的原则。
 *
 * 附:官方library(redisson)   https://github.com/redisson/redisson(锁有很多选择)
 *
 * 上层加锁失败策略:
 * 1.间隔时间再次发送请求，用户点击按钮间隔一段时间可点击
 * 2.请求放入延时队列
 */
@Service
public class DisrtibutedLockService {
    //不存在创建NX, 有过期时间创建EX
    private static final String LOCK_SUCCESS = "OK";

    private static final int SECONS=5;



    @Autowired
    private RedisTemplate redisTemplate;




    /**
     * 添加锁的同时设置过期时间
     */
    @SuppressWarnings("unchecked")
    public boolean addLock(String lockedKey,String randomValue){
            Object result =  redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
                    Jedis jedis = (Jedis) redisConnection.getNativeConnection();
                    SetParams paras = new SetParams();
                    //不存在创建,5秒
                    paras.nx().ex(SECONS);
                    String setResult = jedis.set(lockedKey, randomValue, paras);
                    if (LOCK_SUCCESS.equals(setResult)) {
                        return Boolean.TRUE;
                    }
                    return Boolean.FALSE;
                });
            if(null==result){
                return Boolean.FALSE;
            }
         return (Boolean) result;

    }

    /**
     * 删除时，确保获得的是要删除的那把锁，避免超时问题
     * lua脚本保障原子性
     * @param lockedKey
     * @param randomValue
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean releaseLock(String lockedKey,String randomValue){
        if (null==lockedKey||null==randomValue){
            return false;
        }
        RedisScript<Integer> script =  RedisScript.of(buildScripts(),Integer.class);
        Assert.notNull(script, "Script must not be null!");
        Object result = redisTemplate.execute(script,Collections.singletonList(lockedKey),randomValue);
        if(null==result){
            return false;
        }
        return 1==(Integer)result;
    }

    private   String buildScripts(){
        return "if redis.call('get',KEYS[1])==ARGV[1] then \n"
                +"return redis.call['del',KEYS[1] else \n"
                + "return 0 \n"
                +"end";
    }

}
