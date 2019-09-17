package com.cxy.demo.democors2;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ViewControllerConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
      //unnecessary  registry.addRedirectViewController("/cxy","/test");
        registry.addViewController("/test").setViewName("test-cors");
    }

}
