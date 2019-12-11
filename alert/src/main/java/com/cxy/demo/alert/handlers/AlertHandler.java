package com.cxy.demo.alert.handlers;


import com.cxy.demo.alert.ApiStatInfo;

/**
 * AlertRule 存储告警规则，可以自由设置。
 *
 * Notification 是告警通知类，支持邮件、短信、微信、手机等多种通知渠道。
 *
 */
public abstract class AlertHandler {
    protected AlertRule rule;
    protected Notification notification;
    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }
    public abstract void check(ApiStatInfo apiStatInfo);
}




