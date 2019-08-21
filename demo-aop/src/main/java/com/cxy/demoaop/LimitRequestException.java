package com.cxy.demoaop;

/**
 * 自定义限流异常
 */
public class LimitRequestException extends RuntimeException{


    public LimitRequestException(String msg){
        super(msg);
    }

}
