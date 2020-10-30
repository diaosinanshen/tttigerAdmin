package com.tttiger.admin.common.annotation.validate;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对于方法参数为非包装类的参数提供jsr303校验
 * @author QinHaoTong
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CommonValid {
}
