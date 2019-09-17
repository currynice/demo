package com.cxy.demo.exceptionhandler.controller;



import com.cxy.demo.exceptionhandler.constant.Status;
import com.cxy.demo.exceptionhandler.exception.JsonException;
import com.cxy.demo.exceptionhandler.exception.PageException;
import com.cxy.demo.exceptionhandler.vo.A;
import com.cxy.demo.exceptionhandler.vo.B;
import com.cxy.demo.exceptionhandler.vo.DemoResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.Map;

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


    @GetMapping("/model")
    @ResponseBody
    public String model(Model model) {
        Map<String,Object> info = model.asMap();
        Iterator<Map.Entry<String,Object>> iterator = info.entrySet().iterator();
        if(iterator.hasNext()){
            String key = iterator.next().getKey();

          return  ("key:"+key+";value:"+((Map<String,String>)info.get(key)).get("name"));
        }
        return "";

    }

    //http://localhost:8082/demo/bindData?a.name=%E5%95%8A%E5%95%8A&a.age=20&b.name=%E4%BA%8B%E5%AE%9E%E4%B8%8A&b.age=22
    @GetMapping("/bindData")
    @ResponseBody
    public String bindData(@ModelAttribute("a") A a,@ModelAttribute("b") B b) {
        return a.toString()+">>>"+b.toString();
    }





}
