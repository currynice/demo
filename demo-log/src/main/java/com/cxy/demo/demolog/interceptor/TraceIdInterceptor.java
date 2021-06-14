package com.cxy.demo.demolog.interceptor;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@Component
public class TraceIdInterceptor implements HandlerInterceptor {

    private static final String FLAG = "REQUEST_ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        // 清理该线程之前使用的请求 id
        MDC.clear();

        String traceId = request.getHeader(FLAG);

        if (StrUtil.isEmpty(traceId)) {
            if (null == MDC.get(FLAG)) {
                MDC.put(FLAG, UUID.randomUUID().toString());
            }
        } else {
            MDC.put(FLAG, traceId);
        }

        return true;
    }
}
