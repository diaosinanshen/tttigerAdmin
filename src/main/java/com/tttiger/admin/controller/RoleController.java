package com.tttiger.admin.controller;

import com.tttiger.admin.bean.Menu;
import com.tttiger.admin.bean.Role;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.common.annotation.validate.Add;
import com.tttiger.admin.common.annotation.validate.Update;
import com.tttiger.admin.controller.base.BaseCrudController;
import com.tttiger.admin.service.BaseService;
import com.tttiger.admin.service.MenuService;
import com.tttiger.admin.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 */
@Controller
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController implements BaseCrudController<Role> {

    private RoleService roleService;

    private MenuService menuService;


    @GetMapping("/to-role-add")
    public String toRoleAdd() {
        return "role_add";
    }

    @GetMapping("/to-role-update")
    public String toRoleUpdate() {
        return "role_update";
    }

    @GetMapping("/select-menu")
    public String selectMenu() {
        return "forward:/menu/select-all";
    }

    /**
     * 查询角色拥有菜单
     * @param id 角色id
     * @return 组装好权限菜单
     */
    @GetMapping("/select-role-menu")
    @ResponseBody
    public ResultMap selectRoleMenu(@NotNull(message = "参数不正确") Integer id){
        List<Menu> menus = menuService.selectMenuByRoleId(id);
        return ResultMap.success().data(menus);
    }

    @Override
    public ResultMap add(@RequestBody @Validated(Add.class) Role role) {
        if (roleService.addRoleAndMenu(role)) {
            return ResultMap.success();
        }
        return ResultMap.fail();
    }

    @Override
    public ResultMap update(@RequestBody @Validated(Update.class) Role role) {
        if(roleService.updateRoleMenu(role)){
            return ResultMap.success().message("更新成功");
        }
        return ResultMap.fail().message("更新失败");
    }

    @Override
    public BaseService<Role> getService() {
        return roleService;
    }

}
