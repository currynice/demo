package com.cxy.demo.alert.handlers;

import com.cxy.demo.alert.ApiStatInfo;

/**
 * 每秒接口超时请求个数超过最大的预设阈值,就会触发告警，通知接口的相关负责人或者团队
 */
public class TimeOutTpsAlertHandler extends AlertHandler {

    public TimeOutTpsAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        long timeoutTps = apiStatInfo.getTimeoutCount() / apiStatInfo.getDurationOfSeconds();
        if (timeoutTps > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTimeoutTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
    }
}
