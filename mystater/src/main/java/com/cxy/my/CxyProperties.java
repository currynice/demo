package com.cxy.my;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取application配置文件cxy前缀的配置信息，要提供setter方法
 */
@Component
@ConfigurationProperties(prefix = "cxy")
public class CxyProperties {

    private String initMsg = "hello";

    private boolean show = true;


    public String getInitMsg() {
        return initMsg;
    }

    public void setInitMsg(String initMsg) {
        this.initMsg = initMsg;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
