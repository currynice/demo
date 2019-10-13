package com.cxy.demo.demoasync.service;

/**
 * @Author: cxy
 * @Date: 2019/10/13 16:13
 * @Description:
 */
public class TestException extends RuntimeException {

    public TestException(String message) {
        super(message);
    }

    public TestException(Throwable cause) {
        super(cause);
    }
}
