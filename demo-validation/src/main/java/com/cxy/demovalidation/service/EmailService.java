package com.cxy.demovalidation.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Email;

@Service
@Validated
@Slf4j(topic = "Logger")
public class EmailService {
@Autowired
private ValidService validService;


    public void isEmailValid(@Email String email) throws ConstraintViolationException{

            String s = validService.validParam(email, String.class);
            System.out.println(s);

    }
}
