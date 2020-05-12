package com.cxy.demo.transaction.exceptions;

/**
 * 自定义Exception
 */
public class RollBackException extends Exception {

    public RollBackException() {
        super();
    }

    public RollBackException(String message) {
        super(message);
    }
}
