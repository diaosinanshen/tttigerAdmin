package com.tttiger.admin.controller;

import com.tttiger.admin.bean.Role;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.controller.base.BaseCrudController;
import com.tttiger.admin.service.BaseService;
import com.tttiger.admin.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 秦浩桐
 * @version 1.0
 */
@Controller
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController implements BaseCrudController<Role> {

    private RoleService roleService;

    @GetMapping("/toRole")
    public String toRole() {
        return "role";
    }

    @GetMapping("/to-role-add")
    public String toRoleAdd() {
        return "role_add";
    }

    @GetMapping("/select-menu")
    public String selectMenu() {
        return "forward:/menu/select-all";
    }

    @Override
    public ResultMap add(@RequestBody Role role) {
        if (roleService.addRoleAndMenu(role)) {
            return ResultMap.success();
        }
        return ResultMap.fail();
    }

    @Override
    public BaseService<Role> getService() {
        return roleService;
    }

}
