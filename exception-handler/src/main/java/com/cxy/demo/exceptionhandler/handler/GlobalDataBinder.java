package com.cxy.demo.exceptionhandler.handler;


import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 请求参数处理
 */
@ControllerAdvice
public class GlobalDataBinder {

    //处理@ModelAttribute("a")对应的参数
    @InitBinder("a")
    public void initForA(WebDataBinder webDataBinder){
        webDataBinder.setFieldDefaultPrefix("a.");
    }

    //处理@ModelAttribute("b")对应的参数
    @InitBinder("b")
    public void initForB(WebDataBinder webDataBinder){
        webDataBinder.setFieldDefaultPrefix("b.");
    }
}
