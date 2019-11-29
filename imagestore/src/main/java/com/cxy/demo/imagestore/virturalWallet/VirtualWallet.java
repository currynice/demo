package com.cxy.demo.imagestore.virturalWallet;


import java.math.BigDecimal;

//DDD Domain领域模型(充血模型)
public class VirtualWallet {


    private Long id;
    private Long createTime = System.currentTimeMillis();
    private BigDecimal balance = BigDecimal.ZERO;


    //todo 分布式id生成
    public VirtualWallet(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    //查看余额
    public BigDecimal balance() {
        return this.balance;
    }

    //出账
    public void debit(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("");
        }
        this.balance.subtract(amount);
    }

    //入账
    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException("");
        }
        this.balance.add(amount);
    }

    //todo 冻结
}

