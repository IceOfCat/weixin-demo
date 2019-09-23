package com.adiosava.weixin.common.exception;

import com.adiosava.weixin.common.constant.ErrorCode;

public class MineIllegalStateException extends CommonException {
    public MineIllegalStateException() {
        super(ErrorCode.CommonError.ILLEGAL_STATE, "非法状态");
    }
    public MineIllegalStateException(Exception e) {
        super(ErrorCode.CommonError.ILLEGAL_STATE, e.getMessage(),e);
    }
    @Override
    public MineIllegalStateException setMessage(String message){
        return setMessage(message);
    }

    private static final long serialVersionUID = 1L;

}
