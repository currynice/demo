package com.cxy.performancecounter.v2.viewer;

import com.cxy.performancecounter.v2.stat.RequestStat;

import java.util.Map;

/**
 * Description:   </br>
 * Date: 2021/4/13 22:13
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public interface StatViewer {
    void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills);

}
