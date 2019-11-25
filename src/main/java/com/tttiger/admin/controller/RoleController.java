package com.tttiger.admin.controller;

import com.tttiger.admin.bean.Role;
import com.tttiger.admin.controller.base.BaseCrudController;
import com.tttiger.admin.service.BaseService;
import com.tttiger.admin.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/22 15:58
 * @Description
 */
@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController implements BaseCrudController<Role> {

    private RoleService roleService;

    @GetMapping("/toRole")
    public String toAddManager(){
        return "/role";
    }


    @Override
    public BaseService<Role> getService() {
        return roleService;
    }
}
