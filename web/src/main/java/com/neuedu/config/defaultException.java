package com.neuedu.config;

import com.neuedu.common.ResultJson;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 施子安
 * @create
 * 错误提示
 */
@RestControllerAdvice
public class
defaultException {
    @ExceptionHandler
    ResultJson defaultExceptionHandler(Exception exception){
        exception.printStackTrace();
        if (exception instanceof MyException){
            return  ResultJson.failed(exception.getMessage());
        }
        return ResultJson.failed("系统异常，请联系管理员");
    }
}
