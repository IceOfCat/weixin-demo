package com.adiosava.weixin.common.exception;


/**
 * 全局异常
 * @author lbc
 *
 */
public class PromptException extends RuntimeException {
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

    public PromptException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    protected PromptException(int code, String message, Exception e) {
        super(e);
        this.code = code;
        this.message = message;
    }

    protected PromptException setMessage(String message) {
        this.message = message;
        return this;
    }

    protected PromptException setCode(int code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return "PromptException [code=" + code + ", message=" + message + "]";
    }


}
