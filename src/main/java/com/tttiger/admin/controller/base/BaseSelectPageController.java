package com.tttiger.admin.controller.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxkxfs.official.common.ResultMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/02 11:28
 * @Description
 */
public interface BaseSelectPageController<T> extends BaseController<T> {
    /**
     * 通用实体分页查询
     *
     * @param page  查询页码
     * @param limit 分页大小
     * @return 统一结果封装
     */
    @GetMapping("/select")
    default ResultMap select(@RequestParam(required = false, defaultValue = "1", value = "page") Integer page,
                             @RequestParam(required = false, defaultValue = "10", value = "limit") Integer limit) {
        IPage<T> iPage = new Page<>(page, limit);
        IPage<T> seriesIPage = getService().selectPage(iPage, null);
        return ResultMap.ok().data(seriesIPage);
    }
}
