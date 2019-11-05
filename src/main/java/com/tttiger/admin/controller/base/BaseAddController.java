package com.tttiger.admin.controller.base;

import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.common.annotation.validate.Add;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/02 11:21
 * @Description
 */
public interface BaseAddController<T> extends BaseController<T> {

    /**
     * 通用添加接口
     *
     * @param t      添加实体
     * @param result 参数校验结果
     * @return 统一结果封装
     */
    @PostMapping("/add")
    default ResultMap add(@Validated(Add.class) T t, BindingResult result) {
        return getService().insert(t);
    }

}
