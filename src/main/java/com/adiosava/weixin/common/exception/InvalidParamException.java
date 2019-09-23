package com.adiosava.weixin.common.exception;


import com.adiosava.weixin.common.constant.ErrorCode;

/**
 *  参数异常
 */
public class InvalidParamException extends PromptException {

    private static final long serialVersionUID = 1L;

    public InvalidParamException() {
        super(ErrorCode.CommonError.INVALID_PARAM, "非法参数");
    }

    @Override
    public InvalidParamException setMessage(String string) {
        return (InvalidParamException) super.setMessage(string);
    }

    public InvalidParamException isNull(String name) {
        return setMessage("参数" + name + "不能为空");
    }

}