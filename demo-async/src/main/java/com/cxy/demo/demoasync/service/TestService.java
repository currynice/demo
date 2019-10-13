package com.cxy.demo.demoasync.service;

import org.springframework.stereotype.Service;

/**
 * @Author: cxy
 * @Date: 2019/10/13 16:09
 * @Description:
 */
@Service
public class TestService {

    public void methodWithError(){
        throw new TestException("异常");
    }
}
