package com.cxy.demo.demoresources.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 配置资源访问路径
 * 默认的基础上添加
 */
@Configuration
@Slf4j(topic ="Logger")
public class ResourcesConfiguration implements WebMvcConfigurer {


    @Autowired
    private MyProperties myProperties;

    /**
     * win后面加个/
     * 不要忘了盘符
     * @param registry
     */
    @Override
    public  void addResourceHandlers(ResourceHandlerRegistry registry) {
        //linux
        if(myProperties.getOsType()==1){
            log.info("linux");
            registry.addResourceHandler("/accessory/**").addResourceLocations("file:"+myProperties.getPath());
        }else{
            log.info("win");
            //win  http://localhost:8089/accessory/20190903/aa90fdd0d9e.pdf
            registry.addResourceHandler("/accessory/**").addResourceLocations("file:"+myProperties.getPath());
        }
    }


}
