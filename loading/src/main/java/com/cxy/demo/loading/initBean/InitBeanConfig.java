package com.cxy.demo.loading.initBean;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j(topic = "Logger")
public class InitBeanConfig implements InitializingBean,DisposableBean {
    @Autowired
    MyThread myThread;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("线程{}开始",myThread.getId());
        myThread.start();

    }

    @Override
    public void destroy() throws Exception {
        log.info("项目停止，线程{}中断",myThread.getId());
        myThread.interrupt();
    }
}
