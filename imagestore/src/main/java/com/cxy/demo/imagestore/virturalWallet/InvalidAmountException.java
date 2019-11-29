package com.cxy.demo.imagestore.virturalWallet;



//无效金额
public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(String message) {
        super(message);
    }
}
