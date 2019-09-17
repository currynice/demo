package com.cxy.demo.democors2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * addMapping 表示对哪种格式的请求路径进行跨域处理；
     * allowedHeaders 表示允许的请求头，默认所有的请求头信息；
     * allowedMethods 表示允许的请求方法，默认是 GET POST HEAD; ＊表示支持所有的请求方法；
     * allowedOrigins 示支持的域
     * maxAge 表示探测请求的有效期，在前面的讲解中，读对于 DELETE PUT或者有 自定义头信息的请求，
     * 在执行过程中会先发送探测请求，探测请求不用每次都发,有效期过了之后才会发送探测请求 这个属性，默认是 1800 秒，即 30分钟
     *
     *
     *             response.addHeader("Access-Control-Allow-Origin", "*");
     *             response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
     *             response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
     *             response.addHeader("Access-Control-Max-Age", "1800");//30 min
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/cors/**").allowedHeaders("*").allowedMethods("DELETE","POST").allowedOrigins("http://localhost:8081").maxAge(30);
    }

}
