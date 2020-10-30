package com.tttiger.admin.service.sys;

import com.tttiger.admin.bean.sys.Menu;

import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/12/22 16:46
 * @Description
 */
public interface MenuService extends BaseService<Menu> {

    /**
     * 根据用户账号查询用户所拥有的菜单权限
     * @param account 用户账号
     * @return 用户拥有权限的菜单集合
     */
    List<Menu> selectUserHasAuthorityMenu(String account);

    /**
     * 查询所有菜单列表
     * @return 整合完成的层级菜单
     */
    List<Menu> selectAll();

    /**
     * 查询角色所拥有的菜单
     * @param roleId 角色id
     * @return 未组合的Menu
     */
    List<Menu> selectMenuByRoleId(Integer roleId);
}
