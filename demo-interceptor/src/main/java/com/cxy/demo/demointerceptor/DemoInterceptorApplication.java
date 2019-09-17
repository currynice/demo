package com.cxy.demo.demointerceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoInterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoInterceptorApplication.class, args);
    }


    @GetMapping("/test")
    public String test(){
        System.out.println("test-void");
        return "test-void";
    }

    @GetMapping("/hello")
    public String hello(){
        System.out.println("hello-void");
        return "hello-void";
    }

}
