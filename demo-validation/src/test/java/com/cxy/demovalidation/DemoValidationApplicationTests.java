package com.cxy.demovalidation;

import com.cxy.demovalidation.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoValidationApplicationTests {
    @Autowired
    private EmailService emailService;


    @Test
    public void contextLoads() {
        try {
            emailService.isEmailValid("694975984#qq.com");
        }catch (ConstraintViolationException e){
           e.printStackTrace();
        }

    }

}
