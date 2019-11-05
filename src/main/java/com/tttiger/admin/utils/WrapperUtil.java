
package com.tttiger.admin.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tttiger.admin.common.annotation.wrapper.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 秦浩桐
 * @version 创建时间：2019年4月9日 下午2:44:12
 * <p>
 * 类说明：
 */
@Slf4j
public class WrapperUtil {
    private static final char UNDERLINE = '_';

    private static final Integer DELETED = 1;

    private static final Integer NOt_DELETED = 0;

    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 默认封装,直接根据类名
     *
     * @param t   封装成条件的实体类
     * @param <T> 实体类型
     * @return 返回封装好的wrapper
     */
    public static <T> Wrapper<T> getWrapper(T t, Class... group) {
        return getWrapper(t, "", group);
    }

    /**
     * 根据实体类字段封装wrapper
     *
     * @param t     实体类
     * @param <T>   实体类型
     * @param alias 别名,在join查询时,表名起的别名
     * @return 返回封装好的wrapper
     */
    public static <T> Wrapper<T> getWrapper(T t, String alias, Class... group) {
        List<Field> fields = getHasAutoWrapperField(t.getClass(), group);
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        try {
            for (Field field : fields) {
                String column = alias == null || "".equals(alias) ? "" : alias + ".";
                // 如果指定了表字段名
                if (field.isAnnotationPresent(TableField.class)) {
                    TableField annotation = field.getAnnotation(TableField.class);
                    // 属性存在对应表字段
                    if (annotation.exist()) {
                        // 给出了字段名
                        if (!"".equals(annotation.value())) {
                            column += annotation.value();
                        } else {
                            column += WrapperUtil.camelToUnderline(field.getName());
                        }
                    } else {
                        continue;
                    }
                } else {
                    column += WrapperUtil.camelToUnderline(field.getName());
                }
                Object value = ReflectUtil.getGetterMethod(t, field.getName());
                if (value != null) {
                    Class<? extends WrapperCondition> value1 = field.getAnnotation(AutoWrapper.class).value();
                    createWrapper(value1, queryWrapper, column, value);
                }
            }
        } catch (IllegalArgumentException e) {
            log.warn("自动封装wrapper失败：{}", e.getMessage());
        }
        return queryWrapper;
    }

    private static <T> QueryWrapper<T> createWrapper(Class<? extends WrapperCondition> condition, QueryWrapper<T> wrapper, String column, Object value) {
        if (condition.equals(Eq.class)) {
            wrapper.eq(column, value);
        } else {
            // 不存在等于条件，才有大于小于条件
            if (condition.equals(Lt.class)) {
                wrapper.lt(column, value);
            }
            if (condition.equals(Gt.class)) {
                wrapper.gt(column, value);
            }
        }

        // 封装like条件
        if (condition.equals(Like.class)) {
            wrapper.like(column, value);
        } else if (condition.equals(LikeRight.class)) {
            wrapper.likeRight(column, value);
        } else if (condition.equals(LikeLeft.class)) {
            wrapper.likeLeft(column, value);
        }
        return wrapper;
    }


    private static List<Field> getHasAutoWrapperField(Class clazz, Class[] group) {
        return Arrays.stream(clazz.getDeclaredFields()).filter(x -> {
            if (x.isAnnotationPresent(AutoWrapper.class)) {
                AutoWrapper annotation = x.getAnnotation(AutoWrapper.class);
                Class[] g = (Class[]) annotation.group();
                for (Class c1 : g) {
                    for (Class c2 : group) {
                        if (c1.equals(c2)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }).collect(Collectors.toList());

    }

    private static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
