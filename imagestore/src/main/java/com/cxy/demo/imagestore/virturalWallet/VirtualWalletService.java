package com.cxy.demo.imagestore.virturalWallet;


import java.math.BigDecimal;

public class VirtualWalletService {

    //注入
    private VirtualWalletRepository walletRepo;
    private VirtualWalletTransactionRepository transactionRepo;


    public VirtualWallet getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        //转换函数
        VirtualWallet wallet = convert(walletEntity);
        return wallet;
    }

    //得到余额
    public BigDecimal getBalance(Long walletId) {
        return virtualWalletRepo.getBalance(walletId);
    }

    //出账
    public void debit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.debit(amount);
        walletRepo.updateBalance(walletId, wallet.balance());
    }

    //入账
    public void credit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.credit(amount);
        walletRepo.updateBalance(walletId, wallet.balance());
    }

    //转账
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setFromWalletId(fromWalletId);
        transactionEntity.setToWalletId(toWalletId);
        transactionEntity.setStatus(Status.TO_BE_EXECUTED);
        Long transactionId = transactionRepo.saveTransaction(transactionEntity);
        try {
            debit(fromWalletId, amount);
            credit(toWalletId, amount);
        } catch (InsufficientBalanceException e) {
            //余额不足，转账关闭
            transactionRepo.updateStatus(transactionId, Status.CLOSED);
            throw e;
        } catch (Exception e) {
            //其他异常，转账失败
            transactionRepo.updateStatus(transactionId, Status.FAILED);
            throw e;
        }
            //完成
           transactionRepo.updateStatus(transactionId, Status.EXECUTED);
    }
    }

