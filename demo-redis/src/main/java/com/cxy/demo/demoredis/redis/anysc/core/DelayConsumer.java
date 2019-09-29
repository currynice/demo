package com.cxy.demo.demoredis.redis.anysc.core;


import com.cxy.demo.demoredis.util.RedisKeyUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;


/**
 * 延迟队列客户端消费者
 * 完成重连逻辑，避免长时间闲置，连接被关闭
 * @Author: cxy
 * @Date: 2019/5/11 21:32
 * @Description:  分发Event给Handler
 */
@Service
@Slf4j(topic = "Logger")
public class DelayConsumer implements InitializingBean {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ZSetOperations zSetOperations;



    private Map<EventType, List<DelayHandler>> config2 = new HashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public void afterPropertiesSet() throws Exception {
        //所有实现类
        Map<String,DelayHandler> beans = applicationContext.getBeansOfType(DelayHandler.class);
        if(beans.size()!=0){
           for(Map.Entry<String,DelayHandler> entry:beans.entrySet()){
               List<EventType> eventTypes = entry.getValue().getSupportEventTypes();
                for(EventType type:eventTypes){
                    if(!config2.containsKey(type)){
                        config2.put(type,new ArrayList<DelayHandler>());
                    }
                    config2.get(type).add(entry.getValue());
                }
           }
        }


     Thread thread = new Thread(()->{
             while(!Thread.interrupted()){
                 //只取一条
             String key = RedisKeyUtil.getDelayKey();
             Set<String> values = zSetOperations.rangeByScore(key,0,System.currentTimeMillis(),0,1);
               if(values.isEmpty()){
                   try {
                       Thread.sleep(5000);
                   } catch (InterruptedException e) {
                       //忽略
                       break;
                   }
                   //重试
                   continue;
               }
               String value = values.iterator().next();
               //考虑多个线程争抢，删除成功才最终代表获取成功
                 Long removeOperate = Optional.ofNullable(zSetOperations.remove(key,value)).orElse(-1L);
                 if(removeOperate==1){
                    //抢到了，反序列化
                     ObjectMapper objectMapper = new ObjectMapper();
                     EventModel model = null;
                     try {
                         model = objectMapper.readValue(value, EventModel.class);
                     } catch (IOException e) {
                            break;
                     }
                     if(!config2.containsKey(model.getEventType())){
                         log.error("不能识别的事件");
                         continue;
                     }
                     for(DelayHandler handler:config2.get(model.getEventType())){
                         handler.doHandle(model);
                     }
                 }
             }
     });

        thread.start();
    }
}
