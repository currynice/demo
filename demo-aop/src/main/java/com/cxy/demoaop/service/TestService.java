package com.cxy.demoaop.service;


import com.cxy.demoaop.exceptionhandler.TestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "Logger")
public class TestService {
    public Long get(){
        log.info("get");
        return 1L;
    }

    public String delete(){
        log.info("delete");
        return "result";
    }

    public void exception() throws TestException{
        log.info("exception");
       throw new TestException("出现异常");
    }
}
