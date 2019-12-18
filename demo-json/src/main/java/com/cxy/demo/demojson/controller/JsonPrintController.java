package com.cxy.demo.demojson.controller;


import org.springframework.http.MediaType;
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


    //不加produces就是都支持，因为自动配置仓我们配置了消息转换器
    @ResponseBody
    @GetMapping(value = "/printXml",produces = MediaType.APPLICATION_XML_VALUE)
    public Book printXml(){
        return Book.builder().author("程").tips("提示").date(new Date()).build();
    }
}
