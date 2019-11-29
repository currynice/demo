package com.cxy.demo.imagestore.virturalWallet;


//余额不足
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
