package com.tttiger.admin.controller.sys;

import com.tttiger.admin.common.ResultMap;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author QinHaoTong
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultMap MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return ResultMap.fail().message(objectError.getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultMap DefaultExceptionHandler(Exception e){
        e.printStackTrace();
        return ResultMap.fail();
    }
}