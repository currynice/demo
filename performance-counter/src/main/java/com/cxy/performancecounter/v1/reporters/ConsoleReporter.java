package com.cxy.performancecounter.v1.reporters;

import com.cxy.performancecounter.v1.api.RequestInfo;
import com.cxy.performancecounter.v1.metricsStorage.MetricsStorage;
import com.cxy.performancecounter.v1.stat.Aggregator;
import com.cxy.performancecounter.v1.stat.RequestStat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description: 以一定频率统计并发送统计数据到命令行和邮件。</br>
 *
 *  至于 ConsoleReporter 和 EmailReporter 是否可以抽象出可复用的抽象类，或者抽象出一个公共的接口，我们暂时还不能确定。
 *
 *  1:根据给定的时间区间，从数据源拉取数据；
 *  2:调用Aggregator（使用原始数据），得到计算后的统计数据；
 *  3:将统计数据显示到命令行；
 *  4:定时触发以上 3 个过程的执行。
 *
 * Date: 2021/4/10 12:31
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

public class ConsoleReporter {

    private MetricsStorage metricsStorage;

    private ScheduledExecutorService executor;

    public ConsoleReporter(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }


    /**
     * 第4个代码逻辑：定时触发第1、2、3代码逻辑的执行；
     * @param periodInSeconds
     * @param durationInSeconds
     */
    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
                long durationInMillis = durationInSeconds * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                Map<String, List<RequestInfo>> requestInfos =
                        metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);

                Map<String, RequestStat> stats = new HashMap<>();
                for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
                    String apiName = entry.getKey();
                    List<RequestInfo> requestInfosPerApi = entry.getValue();
                    // 第2个代码逻辑：根据原始数据，计算得到统计数据；
                    RequestStat requestStat = Aggregator.aggregate(requestInfosPerApi, durationInMillis);
                    stats.put(apiName, requestStat);
                }

                // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）；
                System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMillis + "]");
                ObjectMapper mapper = new ObjectMapper();
                try {
                    System.out.println(mapper.writeValueAsString(stats));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }, 0, periodInSeconds, TimeUnit.SECONDS);
    }
}


