package com.cxy.demo.alert.handlers;

import com.cxy.demo.alert.ApiStatInfo;


/**
 * 以及当接口请求出错数大于某个最大允许值时,就会触发告警，通知接口的相关负责人或者团队
 */
public class ErrorAlertHandler extends AlertHandler {
    public ErrorAlertHandler(AlertRule rule, Notification notification){
        super(rule, notification);
    }

//NotificationEmergencyLevel 表示通知的紧急程度，
// 包括 SEVERE（严重）、URGENCY（紧急）、NORMAL（普通）、TRIVIAL（无关紧要），不同的紧急程度对应不同的发送渠道
    @Override
    public void check(ApiStatInfo apiStatInfo) {
        if (apiStatInfo.getErrorCount() > rule.getMatchedRule(apiStatInfo.getApi()).getMaxErrorCount()) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }
    }
}