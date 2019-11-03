package com.tttiger.admin.controller.base;

import com.sxkxfs.official.common.ResultMap;
import org.springframework.web.bind.annotation.GetMapping;

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
    default ResultMap selectOne(@NotNull(message = "id不能为空") Integer id) {
        T t = getService().selectById(id);
        return ResultMap.ok().data(t);
    }
}
