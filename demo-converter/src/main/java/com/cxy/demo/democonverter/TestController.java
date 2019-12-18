package com.cxy.demo.democonverter;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: cxy
 * @Date: 2019/12/18 15:19
 * @Description:
 */
@RestController
public class TestController {

//    @Autowired
//    private TestFormatter formatter;

    @RequestMapping(value = "/test" )
    public String test(String text){
         return text;
    }



}
