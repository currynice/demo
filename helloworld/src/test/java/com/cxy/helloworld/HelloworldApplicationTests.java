package com.cxy.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//SpringRunner通过 定义final 类继承的方式来实现别名
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloworldApplicationTests {

    @Test
    public void contextLoads() {
    }

}
