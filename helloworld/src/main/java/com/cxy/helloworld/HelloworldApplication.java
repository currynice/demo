package com.cxy.helloworld;

import cn.hutool.core.util.StrUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args);
    }


    //visit like /demo/hello?who=cxy
    @GetMapping("hello")
    public String sayHello(@RequestParam(required = false,value = "who")String who){
        if(StrUtil.isBlank(who)){
            return "hello stranger";
        }
        return StrUtil.format("hello{}",who);

    }

}
