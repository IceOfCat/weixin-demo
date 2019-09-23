package com.adiosava.weixin.common.exception;



/**
 * 全局异常
 * @author lbc
 *
 */
public class CommonException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/***
	 * 错误代码
	 */
	private int code;
	/***
	 * 错误信息
	 */
	private String message;

	public CommonException(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	protected CommonException(int code, String message, Exception e) {
		super(e);
		this.code = code;
		this.message = message;
	}


	protected CommonException setMessage(String message) {
		this.message = message;
		return this;
	}

	protected CommonException setCode(int code) {
		this.code = code;
		return this;
	}

	@Override
	public String toString() {
		return "CommonException [code=" + code + ", message=" + message + "]";
	}
	

}
