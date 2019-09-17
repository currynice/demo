package com.cxy.demoaop.controller;

import com.cxy.demoaop.exceptionhandler.TestException;
import com.cxy.demoaop.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController2 {

    @Autowired
    private TestService testService;



    @GetMapping("/get")
    public Long get() {

        return testService.get();
    }


    @GetMapping("/delete")
    public String delete() {

        return testService.delete();
    }

    @GetMapping("/exception")
    public String exception() {
    try {
        testService.exception();
        }catch (TestException e){
        return "出现异常";
    }
        return "无异常";
    }




}
