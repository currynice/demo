package com.cxy.demo.demoredis.redis.anysc.core;

import java.util.List;

/**
 * @Author: cxy
 * @Date: 2019/5/11 18:22
 * @Description: 异步事件处理接口
 */
public interface EventHandler {
    //执行方法
    void doHandle(EventModel model);

    //处理器所关注的事件类型
    List<EventType> getSupportEventTypes();
}
