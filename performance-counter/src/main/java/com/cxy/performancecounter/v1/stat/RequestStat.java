package com.cxy.performancecounter.v1.stat;

import lombok.Data;

/**
 * Description: 计算出的请求统计信息   </br>
 * Date: 2021/4/10 15:31
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Data
public class RequestStat {

    //响应时间最大值
    private double maxResponseTime;

    //响应时间最小值
    private double minResponseTime;

    //响应时间平均值
    private double avgResponseTime;

    //代表99.9%的接口响应时间
    private double p999ResponseTime;

    //代表99%的接口响应时间
    private double p99ResponseTime;

    //接口请求次数
    private long count;

    //tps
    private long tps;
}
