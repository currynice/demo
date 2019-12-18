package com.cxy.demo.demointerceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
//不要用@EnableWebMvc,除非牛逼，自己控制MVC
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/test");
        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**").excludePathPatterns("/test");
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**").excludePathPatterns("/test");
    }

}
