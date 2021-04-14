package com.cxy.performancecounter.v2.metricsStorage;

import com.cxy.performancecounter.v2.api.RequestInfo;

import java.util.List;
import java.util.Map;

/**
 * Description: 负责原始数据存储，为了今后灵活地扩展新的存储方法。  </br>
 * Date: 2021/4/10 12:27
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

public interface MetricsStorage {

    /**
     * 保存采集到的信息
     * @param requestInfo
     */
    void saveRequestInfo(RequestInfo requestInfo);

    /**
     * 根据 时间区间 star-end ,返回 apiName对应的原始数据
     * @param apiName
     * @param startTimeInMillis
     * @param endTimeInMillis
     * @return
     */
    List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis);


    /**
     * 根据 时间区间 star-end ,返回所有原始数据
     * @param startTimeInMillis
     * @param endTimeInMillis
     * @return
     */
    Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);
}


