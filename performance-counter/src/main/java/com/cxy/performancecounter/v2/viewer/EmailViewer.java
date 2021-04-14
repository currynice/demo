package com.cxy.performancecounter.v2.viewer;

import com.cxy.performancecounter.v2.stat.RequestStat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * Description:   </br>
 * Date: 2021/4/13 22:14
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class EmailViewer implements StatViewer {

    List<String> emailToAddresses;

    public EmailViewer(List<String> emailToAddresses) {
        this.emailToAddresses = emailToAddresses;
    }

    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
       //todo ,包装成HTML
    }
}
