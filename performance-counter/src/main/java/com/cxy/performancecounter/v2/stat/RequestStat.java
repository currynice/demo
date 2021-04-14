package com.cxy.performancecounter.v2.stat;

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

    //接口响应时间999百分数 (从小到大排列后，处于0.999*count的那个响应时间)
    private double p999ResponseTime;

    //接口响应时间99百分数 (从小到大排列后，处于0.99*count的那个响应时间)
    private double p99ResponseTime;

    //接口请求次数
    private long count;


    public static RequestStat mockStat(){
        RequestStat stat = new RequestStat();
        stat.setAvgResponseTime(1.0);
        stat.setCount(1);
        stat.setMaxResponseTime(1.0);
        stat.setMinResponseTime(1.0);
        return stat;
    }


}
