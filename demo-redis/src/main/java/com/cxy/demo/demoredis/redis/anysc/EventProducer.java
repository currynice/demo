package com.cxy.demo.demoredis.redis.anysc;


import com.cxy.demo.demoredis.util.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

/**
 * @Author: cxy
 * @Date: 2019/5/11 15:59
 * @Description: 生成队列(reids方式)
 */
@Service
public class EventProducer {
    private static final Logger logger = LoggerFactory.getLogger(EventProducer.class);

    @Autowired
    ListOperations listOperations;

    /**
     * 放入先进后出（does not matter）的队列,顺序执行,如果考虑优先级(请使用Set)
     * @param eventModel
     * @return
     */
    public boolean fireEvent(EventModel eventModel){
        try{
            String eventKey = RedisKeyUtil.getEventKey();
            listOperations.leftPush(eventKey,eventModel);
            logger.info("事件插入队列成功");
          //  BlockingQueue<EventModel> q = new ArrayBlockingQueue<EventModel>();//容器挂了，队列将无法找回
            return true;
        }catch(Exception e){
            return false;
        }

    }
}
