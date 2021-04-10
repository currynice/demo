package com.cxy.performancecounter.v1.stat;

import com.cxy.performancecounter.v1.api.RequestInfo;

import java.util.Collections;
import java.util.List;

/**
 * Description: 根据原始数据计算统计数据  </br>
 * Date: 2021/4/10 12:31
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

public class Aggregator {

    /**
     * 根据取出的原始数据，durationInMillis ，计算durationInMillis内的统计结果
     * @param requestInfos 原始数据
     * @param durationInMillis
     * @return
     */
    public static RequestStat aggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        //最大响应时间(设的很小，这样只要有一个合法的值，即可替代)
        double maxRespTime = Double.MIN_VALUE;

        //最小响应时间(设的很大，这样只要有一个合法的值，即可替代)
        double minRespTime = Double.MAX_VALUE;

        //平均响应时间
        double avgRespTime = -1;

        //可用性99.9百分比
        double p999RespTime = -1;

        //可用性99百分比
        double p99RespTime = -1;

        //总响应时长
        double sumRespTime = 0;

        //调用总数
        long count = 0;

        //解析原始数据 requestInfos
        for (RequestInfo requestInfo : requestInfos) {
            ++count;
            double respTime = requestInfo.getResponseTime();
            if (maxRespTime < respTime) {
                maxRespTime = respTime;
            }
            if (minRespTime > respTime) {
                minRespTime = respTime;
            }
            sumRespTime += respTime;
        }


        if (count != 0) {
            avgRespTime = sumRespTime / count;
        }

        long tps = (long)(count / durationInMillis * 1000);

        //按 responseTime排序
        Collections.sort(requestInfos, (d1,d2) ->{
                double diff = d1.getResponseTime() - d2.getResponseTime();
                if (diff < 0.0) {
                    return -1;
                } else if (diff > 0.0) {
                    return 1;
                } else {
                    return 0;
                }
            });


        int idx999 = (int)(count * 0.999);
        int idx99 = (int)(count * 0.99);

        if (count != 0) {
            p999RespTime = requestInfos.get(idx999).getResponseTime();
            p99RespTime = requestInfos.get(idx99).getResponseTime();
        }


        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(maxRespTime);
        requestStat.setMinResponseTime(minRespTime);
        requestStat.setAvgResponseTime(avgRespTime);
        requestStat.setP999ResponseTime(p999RespTime);
        requestStat.setP99ResponseTime(p99RespTime);
        requestStat.setCount(count);
        requestStat.setTps(tps);
        return requestStat;
    }
}


