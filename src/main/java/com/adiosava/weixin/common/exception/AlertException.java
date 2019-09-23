package com.adiosava.weixin.common.exception;

import com.adiosava.weixin.common.constant.ErrorCode;
import com.adiosava.weixin.util.lang.StringUtil;

/***
 * 前端直接弹出message即可
 *
 */
public class AlertException extends PromptException {

	private static final long serialVersionUID = 1L;

	private String alertMessage;

	private String logErrorMessage;

	public static AlertException getExpection(){
		return new AlertException();
	}

	public AlertException() {
		super(ErrorCode.AlertError.ALERT, "服务器开小差了");
	}

	public AlertException setAlertMessage(String message) {
		this.alertMessage=message;
		return this;
	}

	public AlertException setLogErrorMessage(String message) {
		this.logErrorMessage=message;
		return this;
	}

	public String getAlertMessage(){
		return this.alertMessage;
	}

	public String getLogErrorMessage(){
		return this.logErrorMessage;
	}

	public AlertException setMessage(String format,String a) {
		return (AlertException) super.setMessage(StringUtil.format(format, a));
	}

	@Override
	public AlertException setCode(int code) {
		return (AlertException) super.setCode(code);
	}
}
