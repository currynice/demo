package com.cxy.demo.demolog.conf;

import com.cxy.demo.demolog.interceptor.OrderLogInterceptor;
import com.cxy.demo.demolog.interceptor.TraceIdInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceIdInterceptor()).addPathPatterns("/trace/**").order(0);
        registry.addInterceptor(new OrderLogInterceptor()).addPathPatterns("/order/**").order(0);

    }
}
