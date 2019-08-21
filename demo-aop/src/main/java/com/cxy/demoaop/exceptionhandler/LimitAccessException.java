package com.cxy.demoaop.exceptionhandler;

import lombok.Getter;

/**
 *
 * @description: 限制访问异常
 * @author: cxy
 */
@Getter
public class LimitAccessException extends RuntimeException{


	public LimitAccessException() {
	}

	public LimitAccessException(String message) {
		super(message);
	}


}
