package com.cxy.performancecounter.v2.stat;

import com.cxy.performancecounter.v2.api.RequestInfo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description: 根据原始数据计算统计数据
 *   当有新的统计指标时时候，需要修改 aggregate() 函数代码。
 *   aggregate函数的可读性、可维护性会持续下降。因此，在版本 2 中进行了重构。
 *
 * </br>
 * Date: 2021/4/10 12:31
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

public class Aggregator {

    /**
     *
     * @param requestInfos
     * @return
     */
    public Map<String, RequestStat> aggregate(Map<String, List<RequestInfo>> requestInfos) {

        Map<String, RequestStat> requestStats = new HashMap<>();
        for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
            String apiName = entry.getKey();
            List<RequestInfo> requestInfosPerApi = entry.getValue();
            RequestStat requestStat = doAggregate(requestInfosPerApi);
            requestStats.put(apiName.substring(4), requestStat);
        }
        return requestStats;
    }


    /**
     * 根据取出的原始数据，durationInMillis ，计算durationInMillis(秒)内的统计结果
     * @param requestInfos 原始数据
     * @param
     * @return
     */
    private RequestStat doAggregate(List<RequestInfo> requestInfos) {
        List<Double> respTimes = new ArrayList<>();
        for (RequestInfo requestInfo : requestInfos) {
            double respTime = requestInfo.getResponseTime();
            respTimes.add(respTime);
        }

        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(max(respTimes));
        requestStat.setMinResponseTime(min(respTimes));
        requestStat.setAvgResponseTime(avg(respTimes));
        requestStat.setP999ResponseTime(percentile999(respTimes));
        requestStat.setP99ResponseTime(percentile99(respTimes));
        requestStat.setCount(respTimes.size());
        return requestStat;
    }

    private double percentile999(List<Double> respTimes) {
        double count = respTimes.size();
        int idx999 = (int)(count * 0.999);

        if (count != 0) {
          return   respTimes.stream().sorted().collect(Collectors.toList()).get(idx999);
        }
        return -1D;
    }

    private double percentile99(List<Double> respTimes) {
        double count = respTimes.size();

        int idx99 = (int)(count * 0.99);

        if (count != 0) {
            return   respTimes.stream().sorted().collect(Collectors.toList()).get(idx99);
        }
        return -1D;
    }

    private double avg(List<Double> respTimes) {

        return respTimes.stream().mapToDouble(d->d).average().orElse(-1D);
    }

    private double max(List<Double> respTimes) {

        return respTimes.stream().max((d1,d2)-> Double.compare(d1 -d2, 0.0)).orElse(-1D);
    }

    private double min(List<Double> respTimes) {

        return respTimes.stream().min((d1,d2)-> Double.compare(d1 -d2, 0.0)).orElse(-1D);
    }


    private long tps(int count, long durationInMillis) {

        return (long)(count / durationInMillis * 1000);
    }
}


