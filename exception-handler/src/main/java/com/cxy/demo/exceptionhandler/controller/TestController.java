package com.cxy.demo.exceptionhandler.controller;



import com.cxy.demo.exceptionhandler.constant.Status;
import com.cxy.demo.exceptionhandler.exception.JsonException;
import com.cxy.demo.exceptionhandler.exception.PageException;
import com.cxy.demo.exceptionhandler.vo.DemoResult;
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
