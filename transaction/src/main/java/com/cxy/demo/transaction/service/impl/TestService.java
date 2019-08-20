package com.cxy.demo.transaction.service.impl;

import com.cxy.demo.transaction.exceptions.RollBackException;

public interface TestService {
    void insert() throws RollBackException;
    //方法2
    void insertThenRollback() throws RollBackException;
    //内部调用方法2
    void InvokeInsertThenRollback() throws RollBackException;
}
