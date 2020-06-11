package com.cxy.demo.restful;


import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;



/**
 * Description:   <br>
 * Date: 2020/6/11 11:41  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
@ControllerAdvice
public class APIResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends
            HttpMessageConverter<?>> converterType) {
        return !returnType.getParameterType().equals(ApiResponse.class);
    }

    /**
     * 自动包装 body 为ApiResponse
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType
            selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        String sign = "";
        Sign signAnnotation = returnType.getMethod().getAnnotation(Sign.class);
        if (signAnnotation != null)
            sign = "abcd";
        return new ApiResponse(body, true, "", sign);
    }
}
