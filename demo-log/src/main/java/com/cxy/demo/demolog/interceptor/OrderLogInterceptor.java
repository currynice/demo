package com.cxy.demo.demolog.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 记录操作人：
 *
 *    为了便于获取《操作人》，在用户的拦截器中把用户的标识 Put 到 MDC 中。
 *
 */
@Slf4j
@Component
public class OrderLogInterceptor implements HandlerInterceptor {

    private static final String FLAG = "USER_ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        // 清理该线程之前使用的请求 id
        MDC.clear();
        //获取到用户标识
        String userNo = getUserNo(request);
        //把用户 ID 放到 MDC 上下文中
        MDC.put(FLAG, userNo);
        return true;
    }

    /**
     * 获取到用户标识
     * @param request
     * @return
     */
    private String getUserNo(HttpServletRequest request) {
        // 通过 SSO 或者Cookie 或者 Auth信息获取到 当前登陆的用户信息
        return request.getParameter("userId");
    }
}
