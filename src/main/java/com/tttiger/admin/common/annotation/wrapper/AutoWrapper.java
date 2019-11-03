package com.tttiger.admin.common.annotation.wrapper;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/03 13:21
 * @Description
 */
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoWrapper {
    Class<? extends WrapperCondition> value();
    Class[] group() default {};
}
