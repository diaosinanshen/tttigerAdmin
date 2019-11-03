package com.tttiger.admin.controller.base;

import com.sxkxfs.official.common.ResultMap;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotNull;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/02 11:24
 * @Description
 */
public interface BaseDeleteController<T> extends BaseController<T> {
    /**
     * 根据主键id删除
     *
     * @param id 主键id
     * @return 统一结果封装
     */
    @PostMapping("/delete")
    default ResultMap delete(@NotNull(message = "id不能为空") Integer id) {
        if (getService().delete(id)) {
            return ResultMap.ok();
        }
        return ResultMap.fail();
    }
}
