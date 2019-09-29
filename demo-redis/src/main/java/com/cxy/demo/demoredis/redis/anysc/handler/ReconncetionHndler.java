package com.cxy.demo.demoredis.redis.anysc.handler;


import com.cxy.demo.demoredis.redis.anysc.core.DelayHandler;
import com.cxy.demo.demoredis.redis.anysc.core.DelayProducer;
import com.cxy.demo.demoredis.redis.anysc.core.EventModel;
import com.cxy.demo.demoredis.redis.anysc.core.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


/**
 * example :重连
 * @Author: cxy
 * @Date: 2019/9/29 9:51
 * @Description:
 */
@Component
@Slf4j(topic = "Logger")
public class ReconncetionHndler implements DelayHandler {


    @Override
    public void doHandle(EventModel model) {
        Integer times =  model.getTimes();

            log.info(model.getTaskId()+":第"+times+"次进行重连...");

    }


    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.RECONNECTED);
    }
}
