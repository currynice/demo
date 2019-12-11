package com.cxy.demo.alert.handlers;

import com.cxy.demo.alert.ApiStatInfo;



/**
 * 当接口的 TPS (每秒事务数:一条消息入和一条消息出，加上数据库访问)超过某个预先设置的最大值时,就会触发告警，通知接口的相关负责人或者团队
 */
public class TpsAlertHandler extends AlertHandler {


    public TpsAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    //NotificationEmergencyLevel 表示通知的紧急程度，
    // 包括 SEVERE（严重）、URGENCY（紧急）、NORMAL（普通）、TRIVIAL（无关紧要），不同的紧急程度对应不同的发送渠道
    @Override
    public void check(ApiStatInfo apiStatInfo) {
        long tps = apiStatInfo.getRequestCount()/ apiStatInfo.getDurationOfSeconds();
        if (tps > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
    }

}