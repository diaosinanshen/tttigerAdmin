package com.tttiger.admin.controller.base;


import com.tttiger.admin.service.BaseService;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/02 11:22
 * @Description
 */
public interface BaseController<T> {
    /**
     * 抽象基础controller，通用接口
     *
     * @return 返回调用服务层
     */
    BaseService<T> getService();
}
