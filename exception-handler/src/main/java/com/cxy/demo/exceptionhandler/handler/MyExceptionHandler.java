package com.cxy.demo.exceptionhandler.handler;


import com.cxy.demo.exceptionhandler.exception.JsonException;
import com.cxy.demo.exceptionhandler.exception.PageException;
import com.cxy.demo.exceptionhandler.vo.DemoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

//ControllerAdvice全局异常处理
@ControllerAdvice(annotations = {Controller.class, RestController.class})
@Slf4j
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String DEFAULT_ERROR_VIEW = "error";

    /**
     * 异常返回son
     * ExceptionHandler 默认Exception
     * @param ex
     *
     * @return  {"code":400,"message":"Bad Request","data":null,"timestamp":"2019-07-29 04:18:13"}
     */
    @ExceptionHandler(JsonException.class)
    @ResponseBody
    public DemoResult jsonHandler(JsonException ex){
        log.error("【DemoJsonException】:{}", ex.getMessage());
        return DemoResult.ofException(ex);
    }

    /**
     * 统一 页面 异常处理
     *
     * @param exception PageException
     * @return 统一跳转到异常页面
     */
    @ExceptionHandler(value = PageException.class)
    public ModelAndView pageErrorHandler(PageException exception) {
        log.error("【DemoPageException】:{}", exception.getMessage());
        ModelAndView view = new ModelAndView();
        view.addObject("message", exception.getMessage());
        view.setViewName(DEFAULT_ERROR_VIEW);
        return view;
    }





}
