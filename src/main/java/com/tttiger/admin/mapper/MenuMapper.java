package com.tttiger.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tttiger.admin.bean.Menu;

import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/29 15:54
 * @Description
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 返回所有访问路径,与所需要的权限
     * @return
     */
    List<Menu> selectAllMenuAndRoles();
}
