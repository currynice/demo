package com.cxy.demo.demoredis.jedis;

public class ResultHolder<T> {
    private T resulr;

    public ResultHolder() {
    }

    public T getResulr() {
        return resulr;
    }

    public void setResulr(T resulr) {
        this.resulr = resulr;
    }
}
