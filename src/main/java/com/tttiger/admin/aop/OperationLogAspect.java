package com.tttiger.admin.aop;

import com.tttiger.admin.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author QinHaoTong
 * 记录操作日志
 */
@Aspect
@Component
@Slf4j
public class OperationLogAspect {
    @Around("execution(public * com.tttiger.admin.controller..*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获得切入目标对象
        Object target = pjp.getThis();
        // 取参数
        Object[] args = pjp.getArgs();
        // 获得切入的方法
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        // 输出日期
        log.info("用户：{} 调用该类:{} 的 {} 方法 参数为: [{}]", SecurityUtil.getCurrentUser().getName(), target.getClass().getCanonicalName(), method.getName(), Arrays.toString(args));
        Object proceed = pjp.proceed();
        return proceed;
    }
}

