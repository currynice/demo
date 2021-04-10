package com.cxy.performancecounter.v1.api;

import cn.hutool.core.util.StrUtil;
import com.cxy.performancecounter.v1.metricsStorage.MetricsStorage;

/**
 * Description:  负责提供 API，供业务接口采集请求的原始数据。
 *              可以为 MetricsCollector 抽象出一个接口，但并不是必须的，因为暂时只能想到一个 MetricsCollector 的实现方式 </br>
 * Date: 2021/4/10 12:26
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

public class MetricsCollector {

    ////基于接口而非实现编程
    private MetricsStorage metricsStorage;

    //依赖注入
    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    //用一个函数代替了最小原型中的相似的两个函数
    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StrUtil.isBlank(requestInfo.getApiName())) {
            return;
        }
        metricsStorage.saveRequestInfo(requestInfo);
    }
}
