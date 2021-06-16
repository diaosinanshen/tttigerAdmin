package com.tttiger.admin.aop;

import com.alibaba.fastjson.JSON;
import com.tttiger.admin.utils.SecurityUtil;
import com.tttiger.admin.utils.SnowIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @author QinHaoTong
 * 记录操作日志
 */
@Aspect
@Component
@Slf4j
public class OperationLogAspect {
    private SnowIdGenerator snowIdGenerator = new SnowIdGenerator();

    @Around("execution(public * com.tttiger.admin.controller..*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获得切入目标对象
        Object target = pjp.getThis();
        // 取参数
        Object[] args = pjp.getArgs();
        // 获得切入的方法
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        long flowNo = snowIdGenerator.nextId();
        StringBuilder argsJSON = new StringBuilder();
        for(int i=0;i<args.length;i++){
            if(args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof HttpSession){
                continue;
            }
            argsJSON.append(JSON.toJSON(args[i]));
        }
        // 调用参数日志
        log.info("ip:{} 用户：{} 调用该类:{} 的 {} 方法 参数为: [{}]", flowNo, SecurityUtil.getCurrentUser().getName(),
                target.getClass().getCanonicalName(), method.getName(), argsJSON);
        Object proceed = pjp.proceed();
        // 返回结果日志
        log.info("ip:{} 返回结果：{}", flowNo, JSON.toJSON(proceed));
        return proceed;
    }
}

