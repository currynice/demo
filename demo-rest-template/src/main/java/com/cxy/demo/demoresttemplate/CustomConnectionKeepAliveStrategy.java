package com.cxy.demo.demoresttemplate;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import java.util.Arrays;


//连接复用
public class CustomConnectionKeepAliveStrategy implements ConnectionKeepAliveStrategy {


    private final long DEFAULT_SECONDS = 30L;
    /**
     *    方法要保证线程安全
     *   连接的空闲时间,超过将无法复用，单位毫秒
     *  -1永久有效,避免这种情况
     *
     *  先取response的header的keepAlive
     *  获得timeout,取不到就默认30s,string->Long转换失败也是默认
     */
    @Override
    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
        return Arrays.asList(response.getHeaders(HTTP.CONN_KEEP_ALIVE))
                .stream()
                .filter(h -> StrUtil.equalsIgnoreCase(h.getName(), "timeout")
                        && NumberUtil.isNumber(h.getValue()))
                .findFirst()
                .map(h ->returnLong(h.getValue(), DEFAULT_SECONDS))
                .orElse(DEFAULT_SECONDS) * 1000;
    }



    private static long returnLong(String number,Long defaultNumber) {
        try{
            return NumberUtil.parseLong(number);
        }catch (Exception e){
            return  defaultNumber;
        }
    }



}
