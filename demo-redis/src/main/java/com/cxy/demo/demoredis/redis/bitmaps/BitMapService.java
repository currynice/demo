package com.cxy.demo.demoredis.redis.bitmaps;


import com.cxy.demo.demoredis.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class BitMapService {
    @Autowired
     private ValueOperations valueOperations;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 记录动作
     * @param yyyyMMdd yyyyMMdd格式的时间
     * @param userIdOffset  唯一的
     * @return
     */
    @SuppressWarnings("unchecked")
    public void addtodayLoginLog(Date yyyyMMdd,String actionType,long userIdOffset){
       String key = actionType+":"+DateUtil.dateStr(yyyyMMdd);
       valueOperations.setBit(key,userIdOffset,true);//返回的是旧值
    }

    @SuppressWarnings("unchecked")
    public boolean getTodayLoginLog(Date yyyyMMdd,String actionType,long userIdOffset){
        String key = actionType+":"+DateUtil.dateStr(yyyyMMdd);
        return Optional.ofNullable(valueOperations.getBit(key,userIdOffset)).orElse(Boolean.FALSE);//0假1真
    }

    //bitCount返回设置为1的位的数量
    @SuppressWarnings("unchecked")
    public Long getTodayLoginCount(Date yyyyMMdd,String actionType){
        String key = actionType+":"+DateUtil.dateStr(yyyyMMdd);
       return  (Long)redisTemplate.execute((RedisCallback<Long>)con -> con.bitCount(key.getBytes()));
    }


    /**
     * BITOP 命令支持 AND 、 OR 、 NOT 、 XOR 这四种操作中的任意一种参数：
     *
     * BITOP AND destkey srckey1 … srckeyN ，对一个或多个 key 求逻辑与，并将结果保存到 destkey
     * BITOP OR destkey srckey1 … srckeyN，对一个或多个 key 求逻辑或，并将结果保存到 destkey
     * BITOP XOR destkey srckey1 … srckeyN，对一个或多个 key 求逻辑异或，并将结果保存到 destkey
     * BITOP NOT destkey srckey，对给定 key 求逻辑非，并将结果保存到 destkey
     * 除了 NOT 操作之外，其他操作都可以接受一个或多个 key 作为输入，执行结果将始终保持到destkey里面。
     * @param op
     * @param saveKey
     * @param desKey
     * @return
     */
    public Long bitOp(RedisStringCommands.BitOperation op, String saveKey, String... desKey) {
        byte[][] bytes = new byte[desKey.length][];
        for (int i = 0; i < desKey.length; i++) {
            bytes[i] = desKey[i].getBytes();
        }
        return (Long)redisTemplate.execute((RedisCallback<Long>) con -> con.bitOp(op, saveKey.getBytes(), bytes));
    }

}
