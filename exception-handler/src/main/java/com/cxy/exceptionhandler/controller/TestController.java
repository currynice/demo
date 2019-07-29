package com.cxy.exceptionhandler.controller;



import com.cxy.exceptionhandler.constant.Status;
import com.cxy.exceptionhandler.exception.JsonException;
import com.cxy.exceptionhandler.exception.PageException;
import com.cxy.exceptionhandler.vo.DemoResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @GetMapping("/json")
    @ResponseBody
    public DemoResult jsonException() {
        throw new JsonException(Status.BAD_REQUEST);
    }

    @GetMapping("/page")
    public ModelAndView pageException() {
        throw new PageException(Status.BAD_REQUEST);
    }






}
