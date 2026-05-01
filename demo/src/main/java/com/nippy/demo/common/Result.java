package com.nippy.demo.common;

import lombok.Data;

/**
 * 统一 JSON 结构：前端只认 code + message + data。
 * 约定：code == 0 表示成功，非 0 表示失败（可与 HTTP 状态码一致，也可仅用业务码）。
 */
@Data
public class Result<T> {

    /** 0 成功；非 0 失败（例如 400 参数错误、404 业务不存在） */
    private int code;

    private String message;

    /** 成功时有数据；失败时一般为 null */
    private T data;

    public static <T> Result<T> ok(T data){
        Result<T> r = new Result<>();
        r.setCode(0);
        r.setData(data);
        r.setMessage("ok");
        return r;
    }

    /** 无 data 的成功（如删除成功） */
    public static Result<Void> ok(){
        return ok(null);
    }

    public static <T> Result<T> fail(int code, String message){
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setData(null);
        r.setMessage(message);
        return r;
    }

}
