package com.tttiger.admin.bean.sys;

import lombok.Data;

/**
 * 菜单角色映射表
 * @author qin
 */
@Data
public class RoleMenu {
    private Integer rmId;
    private Integer roleId;
    private Integer menuId;
}
