package com.tttiger.admin.controller.sys;

import com.tttiger.admin.common.ResultMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author QinHaoTong
 */
@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    /**
     * 处理参数校验失败异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultMap<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return ResultMap.data().fail().message(objectError.getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultMap<Object> defaultExceptionHandler(Exception e){
        log.error("产生未处理异常：{}",e.getStackTrace());
        e.printStackTrace();
        return ResultMap.data().fail();
    }
}