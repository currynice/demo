package com.cxy.helloworld;

import cn.hutool.core.util.StrUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @SpringBootApplication{
 * {@link SpringBootConfiguration 相当于 @Configuration ,applicationContext.xml 可以自由配置bean}
 * {@link EnableAutoConfiguration 开启自动化配置,可以覆写替代}
 * {@link ComponentScan 完成包扫描}
 * }
 */
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
            return "hello stranger?";
        }
        return StrUtil.format("hello{}",who);

    }


    //visit like /demo/hello?who=cxy
    @GetMapping("myError")
    public String myError(){
        return "myError";

    }

}
