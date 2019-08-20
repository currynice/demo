package com.cxy.demo.transaction.exceptions;

/**
 * 自定义RunTimeException
 */
public class RollBackRunTimeException extends RuntimeException {

    public RollBackRunTimeException() {
        super();
    }

    public RollBackRunTimeException(String message) {
        super(message);
    }
}
