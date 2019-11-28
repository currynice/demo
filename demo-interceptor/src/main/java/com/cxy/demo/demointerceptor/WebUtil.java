package com.cxy.demo.demointerceptor;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @description 针对网络请求的工具类
 * @author cxy
 * @version 1.1.0
 * @date 2019年10月19日上午9:39:37
 */
public class WebUtil {

    private WebUtil() {
    }

    /**
     * 判断请求是不是ajax 请求
     *
     * X-Requested-With  为：xmlhttprequest
     *
     * @param request
     * @return
     */
    public static boolean isAjax(ServletRequest request){
        String header = ((HttpServletRequest)request).getHeader("X-Requested-With");
        return  "XMLHttpRequest".equalsIgnoreCase(header);
    }

}
