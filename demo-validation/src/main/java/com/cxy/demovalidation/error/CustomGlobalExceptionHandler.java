package com.cxy.demovalidation.error;

import cn.hutool.core.collection.CollUtil;
import com.cxy.demovalidation.bean.DemoResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * ResponseEntityExceptionHandler有对MethodArgumentNotValidException的处理方式，拿来修改一下即可
 * 暂时注释
 */
//@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // error handle for @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

    }



//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public DemoResult resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex){
//        DemoResult errorResult = new DemoResult(111);
//        List<ObjectError>  objectErrors = ex.getBindingResult().getAllErrors();
//        if(!CollUtil.isEmpty(objectErrors)) {
//            StringBuilder msgBuilder = new StringBuilder();
//            for (ObjectError objectError : objectErrors) {
//                msgBuilder.append(objectError.getDefaultMessage()).append(",");
//            }
//            String errorMessage = msgBuilder.toString();
//            if (errorMessage.length() > 1) {
//                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
//            }
//            errorResult.setMsg(errorMessage);
//            return errorResult;
//        }
//        errorResult.setMsg(ex.getMessage());
//        return errorResult;
//    }

}
