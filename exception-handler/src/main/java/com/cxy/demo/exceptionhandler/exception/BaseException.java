package com.cxy.demo.exceptionhandler.exception;

import com.cxy.demo.exceptionhandler.constant.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 异常Basew
 * </p>
 * @description: 异常基类
 * @author: cxy
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
	private Integer code;
	private String message;

	public BaseException(Status status) {
		super(status.getMessage());
		this.code = status.getCode();
		this.message = status.getMessage();
	}

	public BaseException(Integer code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

}
