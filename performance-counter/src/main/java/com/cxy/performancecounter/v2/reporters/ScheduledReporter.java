package com.cxy.performancecounter.v2.reporters;


import com.cxy.performancecounter.v2.api.RequestInfo;
import com.cxy.performancecounter.v2.metricsStorage.MetricsStorage;
import com.cxy.performancecounter.v2.stat.Aggregator;
import com.cxy.performancecounter.v2.stat.RequestStat;
import com.cxy.performancecounter.v2.viewer.StatViewer;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * </br>
 * Date: 2021/4/13 22:39
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

public abstract class ScheduledReporter {


    protected MetricsStorage metricsStorage;
    protected Aggregator aggregator;
    protected StatViewer viewer;

    public ScheduledReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }



    /**
     * 给子类使用
     * 执行对原始数据的聚合计算
     * 然后输出
     * @param startTimeInMillis
     * @param endTimeInMillis
     */
    protected void doStatAndReport(long startTimeInMillis, long endTimeInMillis) {


        //计算聚合数据
        Map<String, List<RequestInfo>> requestInfos =
                metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
        Map<String, RequestStat> requestStats = aggregator.aggregate(requestInfos);
        //输出
        viewer.output(requestStats, startTimeInMillis, endTimeInMillis);
    }





}
