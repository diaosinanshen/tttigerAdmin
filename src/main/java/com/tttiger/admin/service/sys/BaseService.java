package com.tttiger.admin.service.sys;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tttiger.admin.common.ResultMap;

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
    default ResultMap insert(T t) {
        if (getMapper().insert(t) == 1) {
            return ResultMap.success().message("添加成功");
        }
        return ResultMap.fail();
    }


    /**
     * 通过条件查询数据
     *
     * @param wrapper 查询条件
     * @return 查询结果
     */
    default ResultMap selectList(Wrapper<T> wrapper) {
        List<T> ts = getMapper().selectList(wrapper);
        if (ts.isEmpty()) {
            return ResultMap.fail().message("未找到匹配数据").data(ts);
        }
        return ResultMap.success().data(ts);
    }

    /**
     * 条件查询单条数据
     *
     * @param wrapper 查询条件
     * @return 查询结果
     */
    default ResultMap selectOne(Wrapper<T> wrapper) {
        T t = getMapper().selectOne(wrapper);
        if (t == null) {
            return ResultMap.fail().message("未找到匹配数据");
        }
        return ResultMap.fail().message("查询成功").data(t);
    }


    /**
     * 根据主键id查询
     *
     * @param id 主键id
     * @return 查询结果
     */
    default ResultMap selectById(Serializable id) {
        T t = getMapper().selectById(id);
        if (t == null) {
            return ResultMap.fail().message("未找到匹配数据");
        }
        return ResultMap.fail().message("查询成功").data(t);
    }

    /**
     * 分页查询
     *
     * @param page    页码封装
     * @param wrapper 查询条件
     * @return 查询结果
     */
    default ResultMap selectPage(IPage<T> page, Wrapper<T> wrapper) {
        IPage<T> tiPage = getMapper().selectPage(page, wrapper);
        if (tiPage.getRecords().isEmpty()) {
            return ResultMap.fail().message("未找到匹配数据");
        }
        return ResultMap.success().message("查询成功").data(tiPage);
    }


    /**
     * 通过id更新实体
     *
     * @param t 实体
     * @return 是否更新成功
     */
    default ResultMap updateById(T t) {
        if (getMapper().updateById(t) == 1) {
            return ResultMap.success().message("更新成功").data(t);
        }
        return ResultMap.fail().message("更新失败");
    }

    /**
     * 条件更新实体
     * @param t       实体
     * @param wrapper 条件
     * @return 是否更新成功
     */
    default ResultMap update(T t, Wrapper<T> wrapper) {
        if (getMapper().update(t, wrapper) == 1) {
            return ResultMap.success().message("更新成功").data(t);
        }
        return ResultMap.fail().message("更新失败");
    }


    /**
     * 删除实体
     *
     * @param id 主键id
     * @return 是否删除成功
     */
    default ResultMap delete(Serializable id) {
        if (getMapper().deleteById(id) == 1) {
            return ResultMap.success().message("删除成功");
        }
        return ResultMap.fail().message("删除失败");
    }

    /**
     * 按条件删除实体
     *
     * @param wrapper 条件封装
     * @return 是否删除成功
     */
    default ResultMap delete(Wrapper<T> wrapper) {
        int deleted = getMapper().delete(wrapper);
        if(deleted>0){
            return ResultMap.success().message(String.format("成功删除 {} 条", deleted));
        }
        return ResultMap.fail().message("成功 0 条数据");
    }
}
