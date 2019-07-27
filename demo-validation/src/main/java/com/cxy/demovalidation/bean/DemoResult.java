package com.cxy.demovalidation.bean;

import lombok.Data;

/**
 * 
 * @description 请求返回信息的包装类
 * @author cxy
 * @date 2019年7月14日下午10:38:46
 * @version 1.0
 */
@Data
public class DemoResult {
	/**
	 * 错误代码
	 */
	private int code;
	/**
	 * 错误信息
	 */
	private String msg;
	/**
	 * 返回的对象
	 */
	private Object data;

	public DemoResult(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public DemoResult(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}


	public DemoResult(int code) {
		this.code = code;
	}
}
