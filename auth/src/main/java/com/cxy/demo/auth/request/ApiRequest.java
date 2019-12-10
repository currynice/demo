package com.cxy.demo.auth.request;


/**
 *解析 URL，得到 ticket、AppID、时间戳等信息。
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


    public ApiRequest(String baseUrl, String appId, String ticket) {
        this.baseUrl = baseUrl;
        this.appId = getLocalAppId();
        this.ticket = ticket;
        this.timestamp = System.currentTimeMillis();
    }


    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * 获得机器码
     * @return
     */
    public String getAppId() {
        return appId;
    }

    public String getTicket() {
        return ticket;
    }

    public long getTimestamp() {
        return timestamp;
    }

    //todo
    private String getLocalAppId(){
        return "abcde123";
    }


}
