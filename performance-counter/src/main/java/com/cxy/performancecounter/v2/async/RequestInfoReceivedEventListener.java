package com.cxy.performancecounter.v2.async;

import com.cxy.performancecounter.v2.async.event.RequestInfoReceivedEvent;
import com.cxy.performancecounter.v2.metricsStorage.MetricsStorage;
import com.google.common.eventbus.Subscribe;

/**
 * Description: 进程内 RequestInfoReceivedEvent事件 的监听者类  </br>
 *              拿出事件中的请求原始数据，保存下来
 * Date: 2021/4/13 23:15
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class RequestInfoReceivedEventListener {

    //基于接口而非实现编程
    private MetricsStorage metricsStorage;

    public RequestInfoReceivedEventListener(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    /**
     * RequestInfoReceivedEvent事件监听者
     * @param event  以 xxxEvent作为<strong>唯一参数</strong>，并用@Subscribe 标记方法，即可实现该事件的监听者类
     *     使用 @Subscribe 而不是 实现接口,这样避免了单一的方法名(handlerEvent)，开发者可以自由的表达意图。
     */
    @Subscribe
    public void saveRequestInfo(final RequestInfoReceivedEvent event) {
        System.out.println("do saveRequestInfo....");
        if(null==event || null==event.getRequestInfo()){
            return;
        }
        metricsStorage.saveRequestInfo(event.getRequestInfo());
    }
}
