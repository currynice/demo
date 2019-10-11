package com.cxy.demo.demoredis.redis.autoComplete;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@SpringBootTest
@RunWith(SpringRunner.class)
public class AutoCompleteServiceTest {

    @Autowired
    private AutoCompleteService autoCompleteService;

    @Test
    public void test(){
        autoCompleteService.init();
    }

    @Test
    public void out(){
        System.out.println(autoCompleteService.getAutoCompletes("aba",3));
    }

}