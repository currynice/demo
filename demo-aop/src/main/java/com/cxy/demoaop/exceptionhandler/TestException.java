package com.cxy.demoaop.exceptionhandler;

import lombok.Getter;


@Getter
public class TestException extends RuntimeException{


	public TestException() {
	}

	public TestException(String message) {
		super(message);
	}


}
