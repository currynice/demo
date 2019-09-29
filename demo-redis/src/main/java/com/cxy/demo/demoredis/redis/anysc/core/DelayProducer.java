package com.cxy.demo.demoredis.redis.anysc.core;


import com.cxy.demo.demoredis.util.RedisKeyUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * 生产者
 * @Author: cxy
 * @Date: 2019/9/29 15:59
 * @Description: 生成延迟队列(reids方式)
 */
@Service
@Slf4j(topic = "Logger")
public class DelayProducer {


    @Autowired
    private ZSetOperations zSetOperations;

    /**
     * 放入先进后出的队列,顺序执行,如果考虑优先级(请使用ZSet)
     * @param eventModel
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean fireDelay(EventModel eventModel) throws JsonProcessingException {

            String delayKey = RedisKeyUtil.getDelayKey();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = null;
            jsonValue = objectMapper.writeValueAsString(eventModel);
            boolean operateResult = Optional.ofNullable(zSetOperations.add(delayKey,jsonValue,getScore(5000))).orElse(false);
            if (operateResult) {
                log.info("事件插入队列成功");
            }
            return operateResult;

    }

    /**
     * @param delatTime
     * @return
     */
    private  double getScore(double delatTime){
        long now = System.currentTimeMillis();
        return now+delatTime;
    }

}
