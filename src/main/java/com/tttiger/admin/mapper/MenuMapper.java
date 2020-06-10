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


    /**
     * 根据用户账号查询用户所拥有的菜单权限
     * @param account 用户账号
     * @return 用户拥有权限的菜单集合
     */
    List<Menu> selectUserHasAuthorityMenu(String account);

    /**
     * 根据角色id查询角色拥有菜单
     * @param roleId 角色id
     * @return 未拼装好的菜单
     */
    List<Menu> selectMenuByRoleId(Integer roleId);
}
