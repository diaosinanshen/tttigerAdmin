package com.tttiger.admin.service.iml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tttiger.admin.bean.Role;
import com.tttiger.admin.bean.RoleMenu;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.mapper.RoleMapper;
import com.tttiger.admin.mapper.RoleMenuMapper;
import com.tttiger.admin.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateRoleMenu(Role role) {
        roleMapper.updateById(role);
        QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",role.getRoleId());
        roleMenuMapper.delete(wrapper);
        role.getMenus().forEach(x->{
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(role.getRoleId());
            roleMenu.setMenuId(x.getMenuId());
            roleMenuMapper.insert(roleMenu);
        });
        return true;
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

    @Override
    public ResultMap delete(Serializable id) {
        if (getMapper().deleteById(id) == 1) {
            QueryWrapper<RoleMenu> wrapper = new QueryWrapper<>();
            wrapper.eq("role_id",id);
            roleMenuMapper.delete(wrapper);
            return ResultMap.success().message("删除成功");
        }
        return ResultMap.fail().message("删除失败");
    }

    @Override
    public BaseMapper<Role> getMapper() {
        return roleMapper;
    }
}
