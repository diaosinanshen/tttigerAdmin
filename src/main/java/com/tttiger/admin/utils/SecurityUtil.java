package com.tttiger.admin.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/12/22 16:42
 * @Description
 */
public class SecurityUtil {

    /**
     * 获取当前登录的用户
     *
     * @return 当前登录的用户
     */
    public static Authentication getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }


    private SecurityUtil(){}
}
