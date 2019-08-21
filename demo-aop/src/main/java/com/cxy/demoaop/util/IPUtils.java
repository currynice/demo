package com.cxy.demoaop.util;

import javax.servlet.http.HttpServletRequest;

public class IPUtils  {
    private IPUtils(){
        throw new RuntimeException("不可实例化");
    }

    /**
     * 获取ip地址
     * @param request
     * @return
     */
    public static String getIP(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip)?"127.0.0.1":ip;
    }

}
