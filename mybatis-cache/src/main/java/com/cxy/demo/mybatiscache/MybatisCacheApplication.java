package com.cxy.demo.mybatiscache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cxy.demo.mybatiscache")
public class MybatisCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisCacheApplication.class, args);
    }

}
