package com.cxy.demo.dynamicallyswitch;

import com.cxy.demo.dynamicallyswitch.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicallySwitchApplicationTests {
    @Autowired
    private TestService testService;

    @Test
    public void contextLoads() {
        testService.findList1().forEach(System.out::println);
        System.out.println("------");
        testService.findList2().forEach(System.out::println);
    }

}
