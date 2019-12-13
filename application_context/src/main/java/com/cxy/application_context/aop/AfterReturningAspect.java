package com.cxy.application_context.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;




//在FooConfig或者xml中注册
@Aspect
@Slf4j
public class AfterReturningAspect {

    //对所有名为testBeanXXX的实例进行拦截定义
    @AfterReturning("bean(testBean*)")
    public void printAfter() {
        log.info("after hello()");
    }
}
