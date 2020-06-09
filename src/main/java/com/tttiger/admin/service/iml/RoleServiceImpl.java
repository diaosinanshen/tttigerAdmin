package com.tttiger.admin.service.iml;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tttiger.admin.bean.Role;
import com.tttiger.admin.bean.RoleMenu;
import com.tttiger.admin.mapper.RoleMapper;
import com.tttiger.admin.mapper.RoleMenuMapper;
import com.tttiger.admin.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/22 15:59
 * @Description
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleMapper roleMapper;

    private RoleMenuMapper roleMenuMapper;

    @Override
    public BaseMapper<Role> getMapper() {
        return roleMapper;
    }

    @Override
    public boolean addRoleAndMenu(Role role) {
        int temp = roleMapper.insert(role);
        if (role.getMenus() != null && !role.getMenus().isEmpty()) {
            role.getMenus().forEach((x) -> {
                        RoleMenu roleMenu = new RoleMenu();
                        roleMenu.setRoleId(role.getRoleId());
                        roleMenu.setMenuId(x.getMenuId());
                        roleMenuMapper.insert(roleMenu);
                    }
            );
            temp += role.getMenus().size();
        }
        return temp > 0;
    }
}
