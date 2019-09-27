package com.cxy.demo.demoredis.redis.anysc;

import java.util.List;

/**
 * @Author: cxy
 * @Date: 2019/5/11 18:22
 * @Description: 事件处理接口
 */
public interface EventHandler {
    //执行方法
    void doHandle(EventModel model);
    //处理所关注的事件
    List<EventType> getSupportEventTypes();
}
