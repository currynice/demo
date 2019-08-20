package com.cxy.demo.transaction.service;


import com.cxy.demo.transaction.exceptions.RollBackException;
import com.cxy.demo.transaction.service.impl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 默认只对RuntimeException 和Error进行回滚
     * @throws RollBackException
     */
    @Override
    @Transactional
    public void insert() throws RollBackException {
        jdbcTemplate.execute("INSERT INTO test (name) VALUES ('cxy')");
    }

    @Override
    @Transactional(rollbackForClassName = "RollBackException")
    public void insertThenRollback() throws RollBackException {
        jdbcTemplate.execute("INSERT INTO test (name) VALUES ('bbb')");
        throw new RollBackException();

    }

    //只有来自外部的方法调用才会被AOP代理捕获，
    // 也就是，类内部方法调用本类内部的其他方法并不会引起事务行为，即使被调用方法使用@Transactional注解进行修饰。
    @Override
    public void InvokeInsertThenRollback() throws RollBackException {
        insertThenRollback();
    }
}
