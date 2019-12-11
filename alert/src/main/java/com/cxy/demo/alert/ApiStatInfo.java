package com.cxy.demo.alert;


/**
 * 描述Api的状态信息
 *  requestCount         请求数
 *  errorCount          错误计数
 *  durationOfSeconds  持续时间
 *  durationOfSeconds  超时计数
 */
public class ApiStatInfo {

    private String api;

    private long requestCount;

    private long errorCount;

    private long durationOfSeconds;

    private long timeoutCount;


    public ApiStatInfo(String api, long requestCount, long errorCount, long durationOfSeconds, long timeoutCount) {
        this.api = api;
        this.requestCount = requestCount;
        this.errorCount = errorCount;
        this.durationOfSeconds = durationOfSeconds;
        this.timeoutCount = timeoutCount;
    }


    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(long requestCount) {
        this.requestCount = requestCount;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

    public long getDurationOfSeconds() {
        return durationOfSeconds;
    }

    public void setDurationOfSeconds(long durationOfSeconds) {
        this.durationOfSeconds = durationOfSeconds;
    }

    public long getTimeoutCount() {
        return timeoutCount;
    }

    public void setTimeoutCount(long timeoutCount) {
        this.timeoutCount = timeoutCount;
    }
}
