package com.cxy.demovalidation.customexceptions;


/**
 *
 * @description 包装运行时异常
 * @author chengxinyu
 * @date 2019年6月14日下午10:27:33
 * @version 1.0
 */
public class EnumErrorException extends RuntimeException {

    public EnumErrorException(String message) {
           super(message);
    }

    public EnumErrorException(Throwable cause) {
        super(cause);
    }
    public EnumErrorException(String message, Throwable cause) {
        super(message, cause);
    }

}
