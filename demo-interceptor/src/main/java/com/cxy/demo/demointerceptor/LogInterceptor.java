package com.cxy.demo.demointerceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class LogInterceptor implements HandlerInterceptor {
    private ThreadLocal<StopWatch> stopWatch = new ThreadLocal<>();
    private static final Logger log = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StopWatch sw = new StopWatch();
        stopWatch.set(sw);
        sw.start();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        stopWatch.get().stop();
        stopWatch.get().start();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        StopWatch sw = stopWatch.get();
        sw.stop();
        String method = handler.getClass().getSimpleName();
        if (handler instanceof HandlerMethod) {
            String beanType = ((HandlerMethod) handler).getBeanType().getName();
            String methodName = ((HandlerMethod) handler).getMethod().getName();
            method = beanType + "." + methodName;
        }
        log.info("{};{};{};{};{}ms;{}ms;{}ms", request.getRequestURI(), method,
                response.getStatus(), ex == null ? "-" : ex.getClass().getSimpleName(),
                sw.getTotalTimeMillis(), sw.getTotalTimeMillis() - sw.getLastTaskTimeMillis(),
                sw.getLastTaskTimeMillis());
        stopWatch.remove();
    }
}
