package com.cxy.exceptionhandler.constant;


import lombok.Getter;
import org.springframework.lang.Nullable;

/**
 * @description: 状态码枚举
 * @author: cxy
 * {@link org.springframework.http.HttpStatus} 参考HttpStatus
 * https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Status
 *
 * 常用HttpStatus状态：
 * HttpStatus.OK = 200;
 * HttpStatus.BAD_REQUEST = 400;
 * HttpStatus.FORBIDDEN = 403;
 * HttpStatus.NOT_FOUND = 404;
 * HttpStatus.REQUEST_TIMEOUT = 408;
 * HttpStatus.SERVICE_UNAVAILABLE =500;
 */
@Getter
public enum Status {

    //1xx 信息响应,迄今为止的所有内容都是可行的，客户端应该继续请求
    /**
     * 继续
     */
    CONTINUE(100, "Continue{继续}"),
    /**
     * 交换协议,指示服务器也正在切换的协议
     */
    SWITCHING_PROTOCOLS(101, "Switching Protocols{交换协议,服务器也正在切换的协议}"),

    //2xx 成功
    /**
     * OK
     */
    OK(200, "OK"),
    /**
     * 创建
     */
    CREATED(201, "Created{创建}"),
    /**
     * 请求已经接收到，但还未响应，没有结果
     */
    ACCEPTED(202, "Accepted{已接收}"),
    /**
     * 非权威信息
     */
    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information{非权威信息}"),
    /**
     * 没有内容
     */
    NO_CONTENT(204, "No Content{没有内容}"),
    /**
     * 重置内容
     */
    RESET_CONTENT(205, "Reset Content{重置内容}"),
    /**
     * 部分内容
     */
    PARTIAL_CONTENT(206, "Partial Content{部分内容}"),

    // 3xx  重定向

    /**
     * 多种选择
     */
    MULTIPLE_CHOICES(300, "Multiple Choices{多种选择}"),

    /**
     * 永久移动
     */
    MOVED_PERMANENTLY(301, "Moved Permanently"),

    /**
     * 找到
     */
    FOUND(302, "Found"),

    /**
     * 参见其他
     */
    SEE_OTHER(303, "See Other"),

    /**
     * 未修改
     */
    NOT_MODIFIED(304, "Not Modified"),

    /**
     * 暂时重定向
      */
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),

    /**
     * 永久重定向
     */
    PERMANENT_REDIRECT(308, "Permanent Redirect"),

    //4xx客户端错误
    /**
     * 错误请求
     */
    BAD_REQUEST(400, "Bad Request"),
    /**
     * 未经授权
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /**
     * 付费请求
     */
    PAYMENT_REQUIRED(402, "Payment Required"),
    /**
     * 禁止
     */
    FORBIDDEN(403, "Forbidden"),
    /**
     *没找到
     */
    NOT_FOUND(404, "Not Found"),

    /**
     * 方法不允许
     */
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    /**
     * 不可接受
     */
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    /**
     * 需要代理身份验证
     */
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
    /**
     * 请求超时
     */
    REQUEST_TIMEOUT(408, "Request Timeout"),
    /**
     * 指令冲突
     */
    CONFLICT(409, "Conflict"),
    /**
     * 文档永久地离开了指定的位置
     */
    GONE(410, "Gone"),
    /**
     * 需要Content-Length头请求
     */
    LENGTH_REQUIRED(411, "Length Required"),
    /**
     * 前提条件失败
     */
    PRECONDITION_FAILED(412, "Precondition Failed"),
    /**
     * 请求载荷太大
     */
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
    /**
     * URI太长
     */
    URI_TOO_LONG(414, "URI Too Long"),
    /**
     * 不支持的媒体类型
     */
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    /**
     * 请求的范围不可满足
     */
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested range not satisfiable"),
    /**
     * 期望失败
     */
    EXPECTATION_FAILED(417, "Expectation Failed"),
    /**
     * I'm a groot!!!
     */
    I_AM_A_TEAPOT(418, "I'm a groot"),

    /**
     * 资源锁定
     */
    LOCKED(423, "Locked"),
    /**
     * 先前的请求失败，所以此次请求失败
     */
    FAILED_DEPENDENCY(424, "Failed Dependency"),

    /**
     * 请求频繁
     */
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    /**
     * 请求头字段太大
     */
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
    /**
     * 非法资源
     */
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),

    //服务端响应
    /**
     * 不知道如何处理
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    /**
     * 此请求方法不被服务器支持
     */
    NOT_IMPLEMENTED(501, "Not Implemented"),
    /**
     * 错误响应
     */
    BAD_GATEWAY(502, "Bad Gateway"),

    /**
     * 此请求方法不被服务器
     */
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),
    /**
     *服务器不支持请求中所使用的HTTP协议版本。
     */
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported"),


    /**
     * 检测到无限循环
     */
    LOOP_DETECTED(508, "Loop Detected"),
    /**
     * 需要身份验证
     */
    NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required");

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 内容
     */
    private String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }



    //——————

    /**
     * 1xx 信息化
     * 2xx成功
     * 3xx重定向
     * 4xx客户端错误
     * 5xx服务端错误
     * @return
     */
    public Status.Series series() {
        return Status.Series.valueOf(this);
    }

    public boolean is1xxInformational() {
        return this.series() == Status.Series.INFORMATIONAL;
    }

    public boolean is2xxSuccessful() {
        return this.series() == Status.Series.SUCCESSFUL;
    }

    public boolean is3xxRedirection() {
        return this.series() == Status.Series.REDIRECTION;
    }

    public boolean is4xxClientError() {
        return this.series() == Status.Series.CLIENT_ERROR;
    }

    public boolean is5xxServerError() {
        return this.series() == Status.Series.SERVER_ERROR;
    }

    public boolean isError() {
        return this.is4xxClientError() || this.is5xxServerError();
    }

    public String toString() {
        return this.code + " " + this.name();
    }


    public static Status valueOf(int statusCode) {
        Status status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
        } else {
            return status;
        }
    }

    @Nullable
    public static Status resolve(int statusCode) {
        Status[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Status status = var1[var3];
            if (status.code == statusCode) {
                return status;
            }
        }

        return null;
    }

    public static enum Series {
        /**
         * 信息响应
         */
        INFORMATIONAL(1),
        /**
         * 成功
         */
        SUCCESSFUL(2),
        /**
         * 重定向
         */
        REDIRECTION(3),
        /**
         * 客户端错误
         */
        CLIENT_ERROR(4),
        /**
         * 服务端错误
         */
        SERVER_ERROR(5);

        private final int value;

         Series(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }

        public static Status.Series valueOf(Status status) {
            return valueOf(status.code);
        }

        public static Status.Series valueOf(int statusCode) {
            Status.Series series = resolve(statusCode);
            if (series == null) {
                throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
            } else {
                return series;
            }
        }

        @Nullable
        public static Status.Series resolve(int statusCode) {
            int seriesCode = statusCode / 100;
            Status.Series[] var2 = values();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Status.Series series = var2[var4];
                if (series.value == seriesCode) {
                    return series;
                }
            }

            return null;
        }
    }
}
