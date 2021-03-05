package com.tttiger.admin.controller.base;

import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.common.annotation.validate.CommonValid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/02 11:26
 * @Description
 */
public interface BaseSelectOneController<T> extends BaseController<T> {

    /**
     * 根据实体主键id查询
     * @param id 主键id
     * @return 统一结果封装
     */
    @GetMapping("/select/one")
    @ResponseBody
    @CommonValid
    default ResultMap<T> selectOne(@NotNull(message = "id不能为空") Integer id) {
        return getService().selectById(id);
    }
}
