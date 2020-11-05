package com.cxy.demo.demoredis.redis.anysc.core;


import com.cxy.demo.demoredis.util.RedisKeyUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 客户端消费者
 * 1.blocking: 阻塞操作BRPOP ,避免队列空时,空轮询带来的压力
 * 2.完成重连逻辑，因为长时间闲置，连接会被关闭
 * @Author: cxy
 * @Date: 2019/5/11 21:32
 * @Description:  分发Event给Handler
 */
@Service
@Slf4j(topic = "Logger")
public class EventConsumer implements InitializingBean {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ListOperations listOperations;
    /**
     * EventType  ->  List<EventHandler>
     * 事件  -> 事件处理
     * 点赞 ->积分增加，成就值增加
     */
    private Map<EventType, List<EventHandler>> config = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        //所有实现类
        Map<String,EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);
        if(beans.size()!=0){
           for(Map.Entry<String,EventHandler> entry:beans.entrySet()){
               List<EventType> eventTypes = entry.getValue().getSupportEventTypes();
                for(EventType type:eventTypes){
                    if(!config.containsKey(type)){
                        config.put(type,new ArrayList<EventHandler>());
                    }
                    config.get(type).add(entry.getValue());
                }
           }
        }


     Thread thread = new Thread(()->{
             while(true){
               EventModel model =  getEvent();
               if(null==model){
                   continue;
               }
               if(!config.containsKey(model.getEventType())){
                   log.error("不能识别的事件");
                   continue;
               }
               for(EventHandler handler:config.get(model.getEventType())){
                   handler.doHandle(model);
               }
             }
     });

        thread.start();
    }



    //取出事件(BRPOP)
    public EventModel  getEvent(){
        try{
           String key = RedisKeyUtil.getEventKey();
            ObjectMapper objectMapper = new ObjectMapper();
            EventModel eventModel = objectMapper.readValue((String)listOperations.rightPop(key,0,TimeUnit.SECONDS), EventModel.class);
            return  eventModel;//取出最后一个一直取，0代表一直阻塞，直到取到
        }catch (Exception e){
            e.printStackTrace();
            log.error("从队列取出事件失败");
            return null;
        }
    }



}
