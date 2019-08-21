package com.cxy.demo.exceptionhandler.vo;

import com.cxy.demo.exceptionhandler.constant.Status;
import com.cxy.demo.exceptionhandler.exception.BaseException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

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
	private String message;
	/**
	 * 返回的对象
	 */
	private Object data;

	/**
	 * 时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;

	private DemoResult() {
		super();
	}



	private DemoResult(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.timestamp = LocalDateTime.now();
	}


	/**
	 * 构造一个自定义的API返回
	 *
	 * @param code    状态码
	 * @param message 返回内容
	 * @param data    返回数据
	 * @return D
	 */
	public static DemoResult of(Integer code, String message, Object data) {
		return new DemoResult(code, message, data);
	}

	/**
	 * 成功,带数据
	 *
	 * @param data 返回数据
	 * @return
	 */
	public static DemoResult ofSuccess(Object data) {
		return ofStatus(Status.OK, data);
	}

	/**
	 * 构造一个成功且自定义消息的API返回
	 *
	 * @param message 返回内容
	 * @return
	 */
	public static DemoResult ofMessage(String message) {
		return of(Status.OK.getCode(), message, null);
	}

	/**
	 * 有状态的返回
	 *
	 * @param status 状态 {@link Status}
	 * @return
	 */
	public static DemoResult ofStatus(Status status) {
		return ofStatus(status, null);
	}

	/**
	 * 有状态,带数据的返回
	 *
	 * @param status 状态 {@link Status}
	 * @param data   返回数据
	 * @return
	 */
	public static DemoResult ofStatus(Status status, Object data) {
		return of(status.getCode(), status.getMessage(), data);
	}

	/**
	 * 异常,带数据的API返回
	 *
	 * @param t    异常
	 * @param data 数据
	 * @param <T>  {@link BaseException} 的子类
	 * @return
	 */
	public static <T extends BaseException> DemoResult ofException(T t, Object data) {
		return of(t.getCode(), t.getMessage(), data);
	}

	/**
	 * 异常,不带数据的返回
	 *
	 * @param t   异常
	 * @param <T> {@link BaseException} 的子类
	 * @return ApiResponse
	 */
	public static <T extends BaseException> DemoResult ofException(T t) {
		return ofException(t, null);
	}
}
