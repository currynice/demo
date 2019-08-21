package com.cxy.demoaop.exceptionhandler;


import cn.hutool.core.lang.Dict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//ControllerAdvice用来配置控制器通知
@ControllerAdvice(annotations = {Controller.class, RestController.class})
@Slf4j
public class MyExceptionHandler {


    /**
     * 异常返回son
     * ExceptionHandler 默认Exception
     * @param ex
     */
    @ExceptionHandler(LimitAccessException.class)
    @ResponseBody
    public Dict jsonHandler(LimitAccessException ex){
        log.error("【LimitAccessException】:{}", ex.getMessage());
        return Dict.create().set("desc","接口访问限制").set("message",ex.getMessage());
    }



}
