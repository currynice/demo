package com.cxy.demo.demoupload.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import javax.servlet.http.HttpServletRequest;


/**
 * 对Ajax操作的异常处理
 */
@RestControllerAdvice
public class RestUploadExceptionHandler {
    @Autowired
    private MultipartProperties multipartProperties;

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handler(HttpServletRequest request, Throwable ex){
        HttpStatus status = getStatus(request);
        return ("附件上传出错,请上传"+multipartProperties.getMaxRequestSize().toMegabytes()+"MB以下的文件");
    }



    private static HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");

        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        //配对
        return HttpStatus.valueOf(statusCode);
    }
}
