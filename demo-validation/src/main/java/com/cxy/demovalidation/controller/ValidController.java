package com.cxy.demovalidation.controller;


import com.cxy.demovalidation.bean.Book;
import com.cxy.demovalidation.bean.DemoResult;
import com.cxy.demovalidation.service.ValidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j(topic = "Logger")
public class ValidController {


    @Autowired
    private ValidService validService;

    /**
     * 校验失败将抛出MethodArgumentNotValidException 异常
     * 全局异常处理返回所有错误信息
     * curl -v -X POST localhost:8080/books -H "Content-type:application/json" -d "{\"name\":\"ABC\"}"
     * @param newBook
     * @return
     */
    @PostMapping("/books")
    public Book valid1(@Valid @RequestBody Book newBook) {
       return newBook;
    }


    /**
     * 使用自己的 responseresult 只返回第一个错误信息.不全局处理异常
     * @param newBook
     * @return
     */
    @PostMapping("/books2")
    public DemoResult valid2(@Valid @RequestBody Book newBook, BindingResult bindingResult) {
        //返回第一个errormessage
        if (bindingResult.hasErrors()) {
            log.warn(bindingResult.getFieldError().getDefaultMessage());
            return new DemoResult(100, bindingResult.getFieldError().getDefaultMessage());//{"code":100,"msg":"price最低11.23","data":null}
        }
        return new DemoResult(200,"成功",newBook);
        //{"code":200,"msg":"成功","data":{"name":"book1","author":"cxy","price":100.11}}
    }

    /**
     * 返回所有错误信息
     * @param newBook
     * @return
     */
    @PostMapping("/books3")
    public DemoResult valid3(@RequestBody Book newBook) {

        String errorMessage = validService.validParam(newBook);
        if(null!=errorMessage){
            return new DemoResult(111,errorMessage);
        }
        return new DemoResult(200,"成功",newBook);

    }





}
