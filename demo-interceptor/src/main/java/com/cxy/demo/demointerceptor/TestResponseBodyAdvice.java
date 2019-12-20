package com.cxy.demo.demointerceptor;


import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import java.util.Map;
import java.util.Optional;

/**
 * 统一处理请求返回值响应体/@ResponseBody ResponseEntity
 *
 * 在响应体写出之前做一些处理；比如，修改返回值避免一个一个更改或来回变动、加密等。
 */
@RestControllerAdvice
public class TestResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 支持的方法，不做条件判断则所有方法
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.hasMethodAnnotation(ResponseBody.class)&&methodParameter.getMethod().getName().startsWith("testInterceptorForResponseBody");
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        Map<String,String> sourceResponseBody = (Map<String,String>)o;
        //有password,需要马赛克处理
        if((Optional.ofNullable(sourceResponseBody.get("password")).isPresent())){
            sourceResponseBody.put("password","*****");
        }
        return sourceResponseBody;
    }
}
