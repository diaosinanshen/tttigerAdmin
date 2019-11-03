package com.tttiger.admin.controller.base;

import com.sxkxfs.official.common.ResultMap;
import com.sxkxfs.official.validate.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/02 11:24
 * @Description
 */
public interface BaseUpdateController<T> extends BaseController<T> {
    /**
     * 通用更新方法
     * @param t 带主键的更新实体
     * @return 统一结果封装
     */
    @PostMapping("/update")
    default ResultMap update(@Validated(Update.class) T t) {
        if (getService().updateById(t)) {
            return ResultMap.ok();
        }
        return ResultMap.fail();
    }
}
