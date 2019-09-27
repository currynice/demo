package com.cxy.demo.demoredis.redis.anysc.handler;


import com.cxy.demo.demoredis.redis.anysc.EventHandler;
import com.cxy.demo.demoredis.redis.anysc.EventModel;
import com.cxy.demo.demoredis.redis.anysc.EventType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


/**
 * @Author: cxy
 * @Date: 2019/5/12 9:51
 * @Description:
 */
@Component
public class CommentHndler implements EventHandler {
    @Override
    public void doHandle(EventModel model) {

    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.COMMENT);
    }
}
