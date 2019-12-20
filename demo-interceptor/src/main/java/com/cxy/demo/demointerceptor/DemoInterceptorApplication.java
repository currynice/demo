package com.cxy.demo.demointerceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class DemoInterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoInterceptorApplication.class, args);
    }


    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        System.out.println("test-void");
        return "test-void";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("hello-void执行中");
        return "hello-void";
    }

}
