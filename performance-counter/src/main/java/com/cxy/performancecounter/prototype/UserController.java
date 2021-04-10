package com.cxy.performancecounter.prototype;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * Description: 最小需求：统计 注册和登录 接口 的响应时间(max,min,avg)和调用次数（count），并输出到控制台  </br>
 * Date: 2021/4/10 11:09
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Data
public class UserController {

    private Metrics metrics = new Metrics();

    public UserController() {
        metrics.startRepeatedReport(60, TimeUnit.SECONDS);
    }


    public void register(UserVo user) {
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("regsiter", startTimestamp);
        //...
        long respTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("register", respTime);
    }

    public void login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("login", startTimestamp);
        //...
        long respTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("login", respTime);
    }
}
