package com.cxy.performancecounter.v2.async.event;

import com.cxy.performancecounter.v2.api.RequestInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description: 进程内 获得 RequestInfo 事件   </br>
 * Date: 2021/4/14 9:49
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Data
@AllArgsConstructor
public class RequestInfoReceivedEvent {

    private RequestInfo requestInfo;

}
