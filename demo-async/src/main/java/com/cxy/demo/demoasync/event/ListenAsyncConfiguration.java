package com.cxy.demo.demoasync.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * @Author: cxy
 * @Date: 2019/8/14
 * @Description:异步监听配置
 */

@Configuration
@EnableAsync  //开启支持异步处理
@Slf4j(topic = "Logger")
public class ListenAsyncConfiguration implements AsyncConfigurer {

    @Override  //提供线程任务池对象的获取。
    public Executor getAsyncExecutor() {
        //使用Spring内置线程池任务对象
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);// 核心线程数.默认8
        taskExecutor.setMaxPoolSize(20);// 最大线程数
        taskExecutor.setQueueCapacity(25);//队列最大长度
        taskExecutor.initialize();//初始化
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... params) {

                log.warn(method.getName()+"抛出异常:"+throwable.getMessage());

            }
        };
    }


}

