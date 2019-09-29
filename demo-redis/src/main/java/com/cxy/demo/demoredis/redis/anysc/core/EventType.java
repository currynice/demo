package com.cxy.demo.demoredis.redis.anysc.core;

/**
 * @Author: cxy
 * @Date: 2019/5/9 23:45
 * @Description: 定义事件枚举类
 */
public enum EventType {
     LIKE(1),
     COMMENT(2),
    REGISTER(3),
    RECONNECTED(4);

    private Integer typeId;
     EventType(Integer typeId){
     this.typeId = typeId;
    }
    public Integer getEventType(Integer typeId){
         return this.typeId;
    }
}
