package com.cxy.demo.demointerceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/test");
        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**").excludePathPatterns("/test");
    }
}
