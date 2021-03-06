package com.cxy.demo.demoredis.redis.anysc.handler;


import com.cxy.demo.demoredis.redis.anysc.core.EventHandler;
import com.cxy.demo.demoredis.redis.anysc.core.EventModel;
import com.cxy.demo.demoredis.redis.anysc.core.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


/**
 * example :评论
 * @Author: cxy
 * @Date: 2019/5/12 9:51
 * @Description:
 */
@Component
@Slf4j(topic = "Log")
public class CommentHndler implements EventHandler {

    @Override
    public void doHandle(EventModel model) {
        log.info("用户id" + model.getActorId()
                + "关注了你的问题,http://127.0.0.1:8080/question/" + model.getEntityId());
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.COMMENT);
    }
}
