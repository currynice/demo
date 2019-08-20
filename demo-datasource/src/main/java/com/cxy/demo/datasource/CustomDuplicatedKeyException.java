package com.cxy.demo.datasource;

import org.springframework.dao.DataAccessException;
import org.springframework.lang.Nullable;

/**
 * 自定义h2数据库重复主键异常
 * @tips 必须继承 {@link DataAccessException}
 * due to "DataAccessException.class.isAssignableFrom(exceptionClass)"
 */
public class CustomDuplicatedKeyException extends DataAccessException{
    public CustomDuplicatedKeyException(String msg) {
        super(msg);
    }

    public CustomDuplicatedKeyException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }
}
