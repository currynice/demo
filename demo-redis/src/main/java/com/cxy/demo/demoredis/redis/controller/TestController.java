package com.cxy.demo.demoredis.redis.controller;

import com.cxy.demo.demoredis.redis.anysc.core.DelayProducer;
import com.cxy.demo.demoredis.redis.anysc.core.EventModel;
import com.cxy.demo.demoredis.redis.anysc.core.EventProducer;
import com.cxy.demo.demoredis.redis.anysc.core.EventType;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    @Autowired
    EventProducer eventProducer;

    @Autowired
    DelayProducer delayProducer;

    @RequestMapping("testComment")
    public String testComment(){
        EventModel model = new EventModel();
        model.setTaskId(UUID.randomUUID().toString().substring(0,10));
        model.setActorId(11122);
        model.setEntityId(02);
        model.setEntityOwnerId(111);
        model.setEntityType(2);
        model.setEventType(EventType.COMMENT);
        return eventProducer.fireEvent(model)?"入队成功":"入队失败";

    }


    @RequestMapping("testDelay")
    public String testDelay() throws JsonProcessingException {
        EventModel model = new EventModel();
        model.setTaskId(UUID.randomUUID().toString().substring(0,10));
        model.setEntityType(4);
        model.setTimes(1);
        model.setEventType(EventType.RECONNECTED);
        return delayProducer.fireDelay(model)?"延时入队成功":"延时入队失败";

    }
}
