package com.tttiger.admin.controller.base;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/02 11:29
 * @Description
 */
public interface BaseCrudController<T> extends
        BaseAddController<T>,
        BaseDeleteController<T>,
        BaseUpdateController<T>,
        BaseSelectOneController<T>,
        BaseSelectPageController<T>
{

}
