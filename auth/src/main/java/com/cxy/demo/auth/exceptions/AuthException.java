package com.cxy.demo.auth.exceptions;


import cn.hutool.core.util.StrUtil;

public class AuthException extends RuntimeException {
    private static final long serialVersionUID = -1862257739038645376L;

    public AuthException() {
    }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }


    public AuthException(Throwable e, String printTemplate, Object...parms){
        super(StrUtil.format(printTemplate,parms),e);
    }




}
