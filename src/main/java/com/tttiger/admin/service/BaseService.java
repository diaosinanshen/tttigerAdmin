package com.tttiger.admin.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/28 20:02
 * @Description
 */
public interface BaseService<T> {

    /**
     * 实现类提供调用的baseMapper
     *
     * @return baseMapper
     */
    BaseMapper<T> getMapper();

    /**
     * 通用添加一条数据
     *
     * @param t 数据实体
     * @return 是否成功
     */
    default boolean insert(T t) {
        return getMapper().insert(t) == 1;
    }


    /**
     * 通过条件查询数据
     *
     * @param wrapper 查询条件
     * @return 查询结果
     */
    default List<T> selectList(Wrapper<T> wrapper) {
        return getMapper().selectList(wrapper);
    }

    /**
     * 条件查询单条数据
     *
     * @param wrapper 查询条件
     * @return 查询结果
     */
    default T selectOne(Wrapper<T> wrapper) {
        return getMapper().selectOne(wrapper);
    }


    /**
     * 根据主键id查询
     *
     * @param id 主键id
     * @return 查询结果
     */
    default T selectById(Serializable id) {
        return getMapper().selectById(id);
    }

    /**
     * 分页查询
     *
     * @param page    页码封装
     * @param wrapper 查询条件
     * @return 查询结果
     */
    default IPage<T> selectPage(IPage<T> page, Wrapper<T> wrapper) {
        return getMapper().selectPage(page, wrapper);
    }


    /**
     * 通过id更新实体
     *
     * @param t 实体
     * @return 是否更新成功
     */
    default boolean updateById(T t) {
        return getMapper().updateById(t) == 1;
    }

    /**
     * 条件更新实体
     *
     * @param t       实体
     * @param wrapper 条件
     * @return 是否更新成功
     */
    default boolean update(T t, Wrapper<T> wrapper) {
        return getMapper().update(t, wrapper) == 1;
    }


    /**
     * 删除实体
     *
     * @param id 主键id
     * @return 是否删除成功
     */
    default boolean delete(Serializable id) {
        return getMapper().deleteById(id) == 1;
    }

    /**
     * 按条件删除实体
     *
     * @param wrapper 条件封装
     * @return 是否删除成功
     */
    default boolean delete(Wrapper<T> wrapper) {
        return getMapper().delete(wrapper) > 0;
    }
}
