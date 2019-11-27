package com.cxy.demo.exceptioncore;

/**
 * 实现该接口并提供实现，通过异常码，得到异常信息的逻辑
 */
public interface LogicExceptionMessage {

    /**
     * 获得异常消息
     * @param errCode
     * @return
     */
    public String getMessage(String errCode);
}
