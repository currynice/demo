package com.cxy.demo.demoasync.event;



import com.cxy.demo.demoasync.service.TestException;
import com.cxy.demo.demoasync.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


/**
 * @Author: cxy
 * @Date: 2019/9/4
 * @Description:
 */
@Component
@Slf4j(topic = "Logger")
public class TestListener implements SmartApplicationListener {

    @Autowired
    private TestService testService;

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == TestEvent.class;

    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {

        return sourceType== TestService.class;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    @Async
    public void onApplicationEvent(ApplicationEvent applicationEvent){
        //转换事件类型
        TestEvent event = (TestEvent) applicationEvent;
        try {
            System.out.println(event.getName());
            if("error".equals(event.getName())){
                testService.methodWithError();
            }
        } catch (Exception e) {
            log.warn("执行失败:{}",e.getMessage());
        }
        log.info("异步结束");
    }
}
