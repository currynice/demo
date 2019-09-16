package com.cxy.demo.exceptionhandler.handler;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalConfig {
    /**
     * ControllerAdvice全局处理，配合@ModelAttribute注解进行全局数据配置
     * value(userInfo) 为key
     * @return
     */
    @ModelAttribute(value = "userInfo")
    public Map<String,String> info(){
        HashMap<String,String> info = new HashMap<>();
        info.put("name","cxy");
        return info;
    }
}
