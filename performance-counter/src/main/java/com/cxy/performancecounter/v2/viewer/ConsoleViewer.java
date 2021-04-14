package com.cxy.performancecounter.v2.viewer;

import com.cxy.performancecounter.v2.stat.RequestStat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Description:   </br>
 * Date: 2021/4/13 22:14
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class ConsoleViewer implements StatViewer {

    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        // 将统计数据显示到命令行；
        System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMills + "]");
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(requestStats));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
