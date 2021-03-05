package com.tttiger.admin.aop;

import com.tttiger.admin.common.ResultMap;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/29 15:28
 * @Description 参数统一验证
 */
@Aspect
@Component
@Slf4j
public class RequestParamValidAspect {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private final ExecutableValidator validator = factory.getValidator().forExecutables();

    ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    @Around("@annotation(com.tttiger.admin.common.annotation.validate.CommonValid)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        try {
            // 获得切入目标对象
            Object target = pjp.getThis();
            // 取参数
            Object[] args = pjp.getArgs();
            // 获得切入的方法
            Method method = ((MethodSignature) pjp.getSignature()).getMethod();

            // 如果没参数，那肯定不校验了
            if (args.length == 0) {
                return pjp.proceed();
            }

            // 校验普通参数
            // 执行校验，获得校验结果
            Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, args);

            // 如果有校验不通过的
            if (!validResult.isEmpty()) {
                return ResultMap.data().fail().message(validResult.iterator().next().getMessageTemplate());
            }

            return pjp.proceed();
        } catch (Throwable e) {
            log.error("参数校验切面异常:{}", e.getMessage());
            throw e;
        }
    }

    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object[] params) {
        return validator.validateParameters(obj, method, params);
    }
}
