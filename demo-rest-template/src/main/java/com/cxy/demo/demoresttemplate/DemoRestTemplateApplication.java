package com.cxy.demo.demoresttemplate;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DemoRestTemplateApplication {



    public static void main(String[] args) {
//        SpringApplication.run(DemoRestTemplateApplication.class, args);
        new SpringApplicationBuilder().sources(DemoRestTemplateApplication.class)
                                      .bannerMode(Banner.Mode.OFF)//关闭banner
//                                      .web(WebApplicationType.NONE)//非web应用,有时候不需要
                                      .run(args);
    }

}
