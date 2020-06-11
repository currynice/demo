package com.cxy.demo.demoresources.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * 自定义配置属性
 *
 */
@Component
@ConfigurationProperties("demo.resources")
public class MyProperties {

    /**
     * 系统类型 0-win, 1-linux ,默认0
     */
    @Max(value = 1,message = "不支持其他系统")
    @Min(value = 0,message = "不支持其他系统")
    private Integer osType = 0;

    /**
     * 静态资源路径
     */
    private String path;


    public Integer getOsType() {
        return osType;
    }

    public void setOsType(Integer osType) {
        this.osType = osType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
