package com.tttiger.admin.aop;

import com.tttiger.admin.common.ResultMap;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/29 15:28
 * @Description 参数统一验证
 */
@Aspect
@Component
public class RequestParamValidAspect {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private final ExecutableValidator validator = factory.getValidator().forExecutables();

    private Logger logger = LoggerFactory.getLogger(getClass());

    ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    @Around("execution(public com.tttiger.admin.common.ResultMap com.tttiger.admin.controller.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        try {
            // 获得切入目标对象
            Object target = pjp.getThis();
            // 取参数
            Object[] args = pjp.getArgs();
            // 获得切入的方法
            Method method = ((MethodSignature) pjp.getSignature()).getMethod();

            // 输出日期
            logger.info("调用该类:{} 的 {} 方法 参数为: [{}]", target.getClass().getCanonicalName(), method.getName(), Arrays.toString(args));

            // 如果没参数，那肯定不校验了
            if (args.length == 0) {
                return pjp.proceed();
            }

            // 校验封装好的javabean
            // 寻找带BindingResult参数的方法，然后判断是否有error，如果有则是校验不通过
            for (Object arg : args) {
                if (arg instanceof BeanPropertyBindingResult) {
                    // 有校验
                    BeanPropertyBindingResult result = (BeanPropertyBindingResult) arg;
                    if (result.hasErrors()) {
                        List<ObjectError> list = result.getAllErrors();
                        return ResultMap.fail().message(list.get(0).getDefaultMessage());
                    }
                }
            }

            // 校验普通参数
            // 执行校验，获得校验结果
            Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, args);

            // 如果有校验不通过的
            if (!validResult.isEmpty()) {
                return ResultMap.fail().message(validResult.iterator().next().getMessageTemplate());
            }

            return pjp.proceed();
        } catch (Throwable e) {
            logger.error("参数校验切面异常:{}", e.getMessage());
            throw e;
        }
    }

    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object[] params) {
        return validator.validateParameters(obj, method, params);
    }
}
