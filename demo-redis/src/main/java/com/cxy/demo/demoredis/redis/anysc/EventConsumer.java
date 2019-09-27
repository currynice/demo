package com.cxy.demo.demoredis.redis.anysc;


import com.cxy.demo.demoredis.util.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Author: cxy
 * @Date: 2019/5/11 21:32
 * @Description:  分发Event给Handler
 */
@Service
public class EventConsumer implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);

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
     Thread thread = new Thread(new Runnable() {
         @Override
         public void run() {
             while(true){
               EventModel model =  getEvent();
               if(!config.containsKey(model.getEventType())){
                   logger.error("不能识别的事件");
                   continue;
               }
               for(EventHandler handler:config.get(model.getEventType())){
                   handler.doHandle(model);
               }
             }
         }

     });

        thread.start();
    }



    //取出事件
    public EventModel  getEvent(){
        try{
           String key = RedisKeyUtil.getEventKey();
            return  (EventModel) listOperations.rightPop(key,0,TimeUnit.SECONDS);//取出最后一个一直取，直至取到
        }catch (Exception e){
            logger.error("从队列取出事件失败");
            return null;
        }
    }



}
