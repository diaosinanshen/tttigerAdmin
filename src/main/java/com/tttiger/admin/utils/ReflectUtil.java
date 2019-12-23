package com.tttiger.admin.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 秦浩桐
 */
@Slf4j
public class ReflectUtil {

    public static Object getGetterMethod(Object obj, String fieldName) {
        try {
            Method declaredMethod = obj.getClass()
                    .getDeclaredMethod("get" + StringUtil.toUpperCaseFirstOne(fieldName), null);
            return declaredMethod.invoke(obj, null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.warn("反射获取属性失败：{}", e.getMessage());
        }
        return null;
    }


    public static <T> void setObjectField(Object obj, T field, String fieldName) {
        try {
            Method method = obj.getClass().getDeclaredMethod("set" + StringUtil.toUpperCaseFirstOne(fieldName), field.getClass());
            method.invoke(obj, field);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.warn("反射设置属性失败：{}", e.getMessage());
        }
    }


    private ReflectUtil(){}
}
