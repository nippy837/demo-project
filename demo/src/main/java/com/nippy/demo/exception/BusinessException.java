package com.nippy.demo.exception;

/**
 * 可预期的业务错误（如「记录不存在」），由全局处理器转成 Result。
 */
public class BusinessException extends RuntimeException{

    private final int code;

    public BusinessException(int code, String message){
        super(message);
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
