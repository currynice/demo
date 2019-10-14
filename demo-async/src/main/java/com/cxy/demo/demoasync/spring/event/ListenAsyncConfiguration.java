package com.cxy.demo.demoasync.spring.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

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
        //前缀
        taskExecutor.setThreadNamePrefix("executor-01");
        taskExecutor.setCorePoolSize(10);// 核心线程数.默认8
        taskExecutor.setQueueCapacity(25);//队列最大长度，达到核心线程数，将新任务放在queue中
        taskExecutor.setMaxPoolSize(20);// 队列满了，调整核心线程数至最大线程数
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60 * 15);
        // 当最大池都满了，此可伸缩队列策略保证不会丢失新的任务请求，保障应用可伸缩性。default(AbortPolicy,抛出异常),还有丢弃，丢弃最早未使用
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();//初始化(设置前缀和拒绝策略(if null))
        return taskExecutor;
    }


    //自定义Handler处理void方法抛出的异常
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... params) {
                log.info("Method name - " + method.getName());
                log.warn(method.getName()+"抛出异常:"+throwable.getMessage());
            }
        };
    }


}

