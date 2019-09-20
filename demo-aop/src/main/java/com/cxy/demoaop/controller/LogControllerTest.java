package com.cxy.demoaop.controller;

import com.cxy.demoaop.aspect.MethodMeasure;
import com.cxy.demoaop.aspect.Test1;
import com.cxy.demoaop.aspect.Test2;
import com.cxy.demoaop.exceptionhandler.TestException;
import com.cxy.demoaop.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogControllerTest {

    @Autowired
    private TestService testService;



    @MethodMeasure
    @GetMapping("/get")
    @Test1
    @Test2
    public Long get() {

        return testService.get();
    }

    @MethodMeasure
    @GetMapping("/delete")
    public String delete() {

        return testService.delete();
    }

    @MethodMeasure
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
