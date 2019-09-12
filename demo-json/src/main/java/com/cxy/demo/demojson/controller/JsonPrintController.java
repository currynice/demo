package com.cxy.demo.demojson.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;

@Controller
public class JsonPrintController {

    @ResponseBody
    @GetMapping(value = "/print")
    public Book book(){
        return Book.builder().author("程").tips("提示").date(new Date()).build();
    }

    @ResponseBody
    @GetMapping(value = "/print2")
    public Book2 book2(){
        return Book2.builder().author("程").tips("提示").date(new Date()).build();
    }
}
