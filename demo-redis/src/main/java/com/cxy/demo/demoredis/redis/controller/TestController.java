package com.cxy.demo.demoredis.redis.controller;

import com.cxy.demo.demoredis.redis.anysc.core.DelayProducer;
import com.cxy.demo.demoredis.redis.anysc.core.EventModel;
import com.cxy.demo.demoredis.redis.anysc.core.EventProducer;
import com.cxy.demo.demoredis.redis.anysc.core.EventType;
import com.cxy.demo.demoredis.redis.bitmaps.BitMapService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.cxy.demo.demoredis.util.DateUtil.today;

@RestController
public class TestController {

    @Autowired
    EventProducer eventProducer;

    @Autowired
    DelayProducer delayProducer;

    @Autowired
    private BitMapService bitMapService;


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


    @RequestMapping("addLog/{userIdOffSet}")
    public String addLog(@PathVariable(name = "userIdOffSet") Long userIdOffSet) {
         bitMapService.addtodayLoginLog(today(),"comment",userIdOffSet);
        Long total = bitMapService.getTodayLoginCount(today(),"comment");
         return "添加记录成功,第"+total+"名用户";
    }


    @RequestMapping("hasLogin/{userIdOffSet}")
    public String hasLogin(@PathVariable(name = "userIdOffSet") Long userIdOffSet) {
       return bitMapService.getTodayLoginLog(today(),"comment",userIdOffSet)?"登陆了":"没有登陆";

    }


//    /**
//     * json格式返回,authToken:可能需要,ex
//     * @param url           todo 自动百分号编码  https%3A%2F%2Fgithub.com%2F
//     * @param domainId     域名id,可选，默认值:
//     * @param authToken   可能需要
//     * @param expireDate   前端生成 expireDate=2019-03-31, 即2019-03-31失效
//     * @return
//     */
//    @RequestMapping("shortUrl")
//    public String getShortUrl(String url,int domainId,String authToken,String expireDate) {
//        return bitMapService.getTodayLoginLog(today(),"comment",userIdOffSet)?"登陆了":"没有登陆";
//
//    }

}
