package com.cxy.demo.demomybatis.controller;

import com.cxy.demo.demomybatis.dao.CoffeeMapper;
import com.cxy.demo.demomybatis.entity.Coffee;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class RequestController {

    @Autowired
    private CoffeeMapper coffeeMapper;

    /**
     * test for startPage
     * url like  test?pageNum=1&pageSize=3
     * {@link PageHelper#startPage(Object params)}
     * @return
     */
    @RequestMapping("/test")
    public ResponseEntity test(HttpServletRequest request){
        PageHelper.startPage(request);
        List<Coffee> coffees = coffeeMapper.findAll();
        ResponseEntity responseEntity = new ResponseEntity(coffees, HttpStatus.OK);
        return responseEntity;
    }
}
