package com.nippy.demo.exception;

import com.nippy.demo.common.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 集中处理异常：转成统一 Result，避免每个 Controller try-catch。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** @Valid 校验失败（如 title 为空） */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleValid(MethodArgumentNotValidException e){

        /**
         * 从参数校验异常里，拿出所有字段错误
         * 把每个错误变成：字段名: 错误信息
         * 最后把所有错误用 : 连起来，变成一段完整的提示语
         */
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return Result.fail(400, msg);
    }

    /** 业务异常 */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusiness(BusinessException e){
        return Result.fail(e.getCode(), e.getMessage());
    }

    /** 兜底：未分类异常（上线后可改为不暴露详细错误给前端） */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleOther(Exception e){
        return Result.fail(500, "服务器内部错误" + e.getMessage());
    }
}
