package com.cxy.demo.demointerceptor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("interceptorForResponseBody")
public class TestController {

    /**
     * 对@ResponseBody 和 ResponseEntity的拦截
     * @return
     */
    @RequestMapping("test")
    @ResponseBody
    public ResponseEntity testInterceptorForResponseBody(){
        Map<String,String> body = new HashMap<>();
        body.put("password","123456");
        body.put("message","hello");
        ResponseEntity responseEntity = new ResponseEntity(body, HttpStatus.OK);
        return responseEntity;
    }


    @RequestMapping("test2")
    @ResponseBody
    public ResponseEntity testInterceptorForResponseBody2(){
        Map<String,String> body = new HashMap<>();
        body.put("pwd","123456");
        body.put("message","hello");
        ResponseEntity responseEntity = new ResponseEntity(body, HttpStatus.OK);
        return responseEntity;
    }
}
