package com.cxy.demo.demoredis.redis.anysc.core;


import com.cxy.demo.demoredis.util.RedisKeyUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 生产者
 * @Author: cxy
 * @Date: 2019/5/11 15:59
 * @Description: 生成异步队列(reids方式)
 */
@Service
public class EventProducer {
    private static final Logger logger = LoggerFactory.getLogger(EventProducer.class);

    @Autowired
    ListOperations listOperations;

    /**
     * 放入先进后出的队列,顺序执行,如果考虑优先级(请使用ZSet)
     * @param eventModel
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean fireEvent(EventModel eventModel){
        try{
            String eventKey = RedisKeyUtil.getEventKey();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(eventModel);
            Long operateResult = Optional.ofNullable(listOperations.leftPush(eventKey,jsonValue)).orElse(-1L);
            if (operateResult>0) {
                logger.info("事件插入队列成功");
            }
          //  BlockingQueue<EventModel> q = new ArrayBlockingQueue<EventModel>();//容器挂了，此内存中实现的队列将无法找回
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
