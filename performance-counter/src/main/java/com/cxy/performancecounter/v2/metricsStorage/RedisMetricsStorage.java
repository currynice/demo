package com.cxy.performancecounter.v2.metricsStorage;

import com.cxy.performancecounter.utils.SpringCtxUtil;
import com.cxy.performancecounter.v2.api.RequestInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Description: </br>
 * Date: 2021/4/10 12:30
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class RedisMetricsStorage implements MetricsStorage {


    //业务前缀
    private static final String Business_Prefix = "api@";




    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {
        //zadd
        String apiName = requestInfo.getApiName();
        ZSetOperations<String,Object> zSetOperations = SpringCtxUtil.getBean(ZSetOperations.class);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String value = objectMapper.writeValueAsString(requestInfo);
            zSetOperations.add(Business_Prefix+apiName,value,requestInfo.getTimestamp());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimestamp, long endTimestamp) {
        //...
        ObjectMapper objectMapper = new ObjectMapper();
        List<RequestInfo> requestInfos = new ArrayList<>();
        ZSetOperations<String,Object> zSetOperations = SpringCtxUtil.getBean(ZSetOperations.class);
        Set<Object> datas = zSetOperations.rangeByScore(Business_Prefix+apiName, startTimestamp, endTimestamp);
        if(datas==null){
            return requestInfos;
        }
        for (Object obj : datas) {
            RequestInfo requestInfo = null;
            try {
                requestInfo = objectMapper.readValue((String) obj, RequestInfo.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            requestInfos.add(requestInfo);
        }
        return requestInfos;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimestamp, long endTimestamp) {

        Map<String, List<RequestInfo>> map = new LinkedHashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();


        RedisTemplate<String,Object> redisTemplate = SpringCtxUtil.getBean(RedisTemplate.class);
        //todo : change to scan
        Set<String> keyset = redisTemplate.keys(Business_Prefix+"*");
        if(null==keyset){
            return map;
        }

        ZSetOperations<String,Object> zSetOperations = SpringCtxUtil.getBean(ZSetOperations.class);

        for (String key : keyset) {
            Set<Object> datas = zSetOperations.rangeByScore(key, startTimestamp, endTimestamp);

            List<RequestInfo> requestInfos = datas.stream().map(data->{
                try {
                    return  objectMapper.readValue((String) data, RequestInfo.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());

               map.put(key, requestInfos);
        }
        return map;
    }
}
