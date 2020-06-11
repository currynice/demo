package com.cxy.demo.democonverter;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    //localhost:8080/enum?type=TOY 可以
    //localhost:8080/enum?type=Toy 可以
    //localhost:8080/enum?type=ToY 可以
    @GetMapping("enum")
    public ItemTypeEnum search(@RequestParam("type") ItemTypeEnum itemTypeEnum) {
        return itemTypeEnum;
    }

}
