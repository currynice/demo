package com.cxy.demovalidation.controller;


import com.cxy.demovalidation.bean.Book;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j(topic = "Logger")
@Validated  //type Level
public class ValidatedController {

    //————————————对pathVariable 路径变量 或者单个requestParam参数，甚至service方法中的参数，也可以进行校验
    //————————————Step1: classLevel使用@Validate
    //————————————Step2:尽情使用 javax.validation.constraints.*

    /**
     *
     * 对pathVariables进行校验
     * validated 失败将抛出ConstraintViolationException,可以处理这个异常并改写我们期望的返回信息
     * 这个是默认的
     {"timestamp":"2019-07-27T07:10:56.935+0000","
     status":500,"error":"Internal Server Error",
     "message":"valid1.name: name超过最大长度",
     "path":"/validated/books/11111"}
     */
    @PostMapping("/validated/books/{name}")
    public Book valid1(@Length(max = 3,message ="name超过最大长度") @PathVariable String name) {
       return new Book(name);
    }

    /**localhost:8080/validated/books2?name=11111
     * 对requestParam 进行校验
     * @param name  11111
     * @return
    {"timestamp":"2019-07-27T07:21:12.602+0000",
    "status":400,error":"Bad Request","
    message":"valid2.name: name超过最大长度","path":"/validated/books2"}
     */
    @PostMapping("/validated/books2")
    public Book valid2(@Length(max = 3,message ="name超过最大长度") @RequestParam String name) {
        return new Book(name);
    }






}
