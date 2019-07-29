package com.cxy.exceptionhandler.exception;


import com.cxy.exceptionhandler.constant.Status;
import lombok.Getter;

/**
 * @description: 处理页面异常
 * @author: cxy
 */
@Getter
public class PageException extends BaseException {

	public PageException(Status status) {
		super(status);
	}

	public PageException(Integer code, String message) {
		super(code, message);
	}
}
