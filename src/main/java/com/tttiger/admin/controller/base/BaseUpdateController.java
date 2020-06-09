package com.tttiger.admin.controller.base;

import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.common.annotation.validate.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/02 11:24
 * @Description
 */
public interface BaseUpdateController<T> extends BaseController<T> {
    /**
     * 通用更新方法
     *
     * @param t 带主键的更新实体
     * @return 统一结果封装
     */
    @PostMapping("/update")
    @ResponseBody
    default ResultMap update(@Validated(Update.class) T t) {
        return getService().updateById(t);
    }
}
