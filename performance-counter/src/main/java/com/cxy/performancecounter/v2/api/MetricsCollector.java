package com.cxy.performancecounter.v2.api;

import cn.hutool.core.util.StrUtil;
import com.cxy.performancecounter.v2.async.DeadEventListener;
import com.cxy.performancecounter.v2.async.event.RequestInfoReceivedEvent;
import com.cxy.performancecounter.v2.async.RequestInfoReceivedEventListener;
import com.cxy.performancecounter.v2.metricsStorage.MetricsStorage;
import com.cxy.performancecounter.v2.metricsStorage.RedisMetricsStorage;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import java.util.concurrent.Executors;

/**
 * Description:  负责提供 API，供业务接口采集请求的原始数据。
 *              可以为 MetricsCollector 抽象出一个接口，但并不是必须的，因为暂时只能想到一个 MetricsCollector 的实现方式
 *
 *              采集和存储要异步执行，可以降低对接口响应时间的影响。
 *              因此引入 Google Guava EventBus (内存共享队列实现的“生产者 - 消费者”模型，
 *              采集的数据先放入内存共享队列中，
 *              另一个线程读取共享队列中的数据，写入到外部存储中</br>
 * Date: 2021/4/10 12:26
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

public class MetricsCollector {

    private static final int DEFAULT_STORAGE_THREAD_POOL_SIZE = 20;


    private EventBus asyncEventBus;


    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
    public MetricsCollector() {
        this(new RedisMetricsStorage());
    }


    // 兼顾灵活性和代码的可测试性，这个构造函数继续保留
    public MetricsCollector(MetricsStorage metricsStorage) {
        this(metricsStorage, DEFAULT_STORAGE_THREAD_POOL_SIZE);
    }

    // 兼顾灵活性和代码的可测试性，这个构造函数继续保留
    public MetricsCollector(MetricsStorage metricsStorage, int threadNumToSaveData) {
        //初始化EventBus
        this.asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(threadNumToSaveData),
                (throwable, context)->{

                  System.out.println(context.getSubscriberMethod());

                    }
             );

        //区别于 传统的方式需要在依次在所有生产者实例上注册
        //直接在同一个 eventBus 对象（队列）上注册 ,撤销注册是 unregister
        this.asyncEventBus.register(new RequestInfoReceivedEventListener(metricsStorage));
        this.asyncEventBus.register(new DeadEventListener());
    }



    //用一个函数代替了最小原型中的相似的两个函数
    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StrUtil.isBlank(requestInfo.getApiName())) {
            return;
        }
        //wrap a requestinfo object to an Event
        RequestInfoReceivedEvent event = new RequestInfoReceivedEvent(requestInfo);
        //期待异步执行(因为是异步分发的AsyncEventBus)
        asyncEventBus.post(event);
    }
}
