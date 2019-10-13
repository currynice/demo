package com.cxy.demo.demoasync.event;


import org.springframework.context.ApplicationEvent;
/**
 * @Author: cxy
 * @Date: 2019/6/14 16:20
 * @Description:
 */
public class TestEvent extends ApplicationEvent {

    private  String name;

    public String getName() {
        return name;
    }

    public TestEvent(Object source,String name) {
        super(source);
        this.name = name;
    }
}
