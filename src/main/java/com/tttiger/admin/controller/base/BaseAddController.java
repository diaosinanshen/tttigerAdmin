package com.tttiger.admin.controller.base;

import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.common.annotation.validate.Add;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @return 统一结果封装
     */
    @PostMapping("/add")
    @ResponseBody
    default ResultMap add(@Validated(Add.class) T t) {
        return getService().insert(t);
    }

}
