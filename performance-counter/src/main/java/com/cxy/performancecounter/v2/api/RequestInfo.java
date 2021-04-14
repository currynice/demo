package com.cxy.performancecounter.v2.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:   </br>
 * Date: 2021/4/10 12:35
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestInfo {
    private String apiName;

    //响应时间
    private double responseTime;

    //请求开始处理的时间戳
    private long timestamp;
}
