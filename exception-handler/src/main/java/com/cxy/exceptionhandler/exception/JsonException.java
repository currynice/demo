package com.cxy.exceptionhandler.exception;

import com.cxy.exceptionhandler.constant.Status;
import lombok.Getter;

/**
 *
 * @description: 处理JSON异常
 * @author: cxy
 */
@Getter
public class JsonException extends BaseException {


	public JsonException(Status status) {
		super(status);
	}

	public JsonException(Integer code, String message) {
		super(code, message);
	}
}
