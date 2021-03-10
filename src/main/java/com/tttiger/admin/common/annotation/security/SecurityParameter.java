package com.tttiger.admin.common.annotation.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对controller层方法参数进行AES解密，对返回内容进行AES加密
 * @author QinHaoTong
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityParameter {
    /**
     * 接收参数是否解密
     */
    boolean decrypt() default true;

    /**
     * 返回参数是否加密
     */
    boolean encrypt() default true;
}
