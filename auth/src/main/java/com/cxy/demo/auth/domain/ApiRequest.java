package com.cxy.demo.auth.domain;


/**
 *
 * 1.将 ticket、AppID、时间戳拼接到 URL 中，形成新的 URL；
 * 2.解析 URL，得到 ticket、AppID、时间戳等信息。
 */
public class ApiRequest {

    //完整url 包含请求参数等
    private String baseUrl;

    //设备编号
    private String appId;

    //t票
    private String ticket;

    //请求时间戳
    private long timestamp;


    public ApiRequest(String baseUrl, String appId, String ticket, long timestamp) {
        this.baseUrl = baseUrl;
        this.appId = appId;
        this.ticket = ticket;
        this.timestamp = timestamp;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getAppId() {
        return appId;
    }

    public String getTicket() {
        return ticket;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public static ApiRequest createFromFullUrl(String url){

    }
}
