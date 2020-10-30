package com.tttiger.admin.service.sys;

import com.tttiger.admin.bean.sys.Role;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/22 15:59
 * @Description
 */
public interface RoleService extends BaseService<Role> {
    /**
     * 添加角色并添加角色拥有的权限菜单
     * @param role 又有权限菜单的角色
     * @return 影响条数
     */
    boolean addRoleAndMenu(Role role);

    /**
     * 更新角色与角色权限
     * @param role
     * @return
     */
    boolean updateRoleMenu(Role role);
}
