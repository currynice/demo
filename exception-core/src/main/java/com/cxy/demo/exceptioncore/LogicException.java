package com.cxy.demo.exceptioncore;

import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 异常Basew
 * </p>
 * @description: 异常基类
 * protected:包内以及子类可见
 * @author: cxy
 */
@EqualsAndHashCode(callSuper = true)
public class LogicException extends RuntimeException {

	/**
	 * 日志对象
	 */
	private Logger logger = LoggerFactory.getLogger(LogicException.class);


	/**
	 * 错误码枚举的值
	 */
	protected String errCode;

	/**
	 * 错误消息内容
	 */
	protected String errMsg;


	/**
	 * 参数列表
	 */
	protected String[] params;


	public String getErrCode() {
		return errCode;
	}


	public String getErrMsg() {
		return errMsg;
	}

	public String[] getParams() {
		return params;
	}


	public LogicException(String errCode, String ...params) {
		this.errCode = errCode;
		this.params = params;
		//格式化出的errMsg
		this.errMsg = ErrorMessageTools.getErrorMessage(errCode,params);

		//错误信息
		logger.error("出现异常，异常码：{}>>>异常信息：{}",	errCode,errMsg);
	}
}
