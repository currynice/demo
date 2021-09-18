package com.cxy.demo.demolog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <h1>日志链路追踪案例</h1>
 * */
@Slf4j
@RestController
@RequestMapping("/trace")
public class TraceRequestController {

    @Autowired
    private  RestTemplate restTemplate;


    /**
     * 向 另一个服务发起请求
     * */
    @GetMapping("/request")
    public String request() {

        String url = "http://127.0.0.1:9527/api/xxxx"; //todo
        log.info("send request to 9527, status code is: [{}]",
                restTemplate.getForEntity(url, Void.class).getStatusCodeValue());
        return "ok";
    }
}
