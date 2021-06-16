package com.tttiger.admin.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author QinHaoTong
 * 菜单页面跳转控制
 */
@Controller
public class JumpController {

    @GetMapping("/welcome")
    public String toWelcome(){
        return "welcome";
    }

    @GetMapping("/role")
    public String toRole(){
        return "role";
    }

    @GetMapping("/role/to-role-add")
    public String toRoleAdd() {
        return "role_add";
    }

    @GetMapping("/role/to-role-update")
    public String toRoleUpdate() {
        return "role_update";
    }

    @GetMapping("/manager")
    public String toManager(){
        return "manager";
    }

    @GetMapping("/manager/to-manager-add")
    public String toManagerAdd(){
        return "manager_add";
    }

    @GetMapping("/manager/to-change-pwd")
    public String toChangePassword(){
        return "change_password";
    }

    @GetMapping("/manager/to-manager-authority")
    public String toManagerAuthority(){
        return "manager_authority";
    }

    @GetMapping("/login")
    public String toLoginPage(){
        return "login";
    }

    @GetMapping("/admin")
    public String toAdminPage(){
        return "admin";
    }

    @GetMapping("/menu")
    public String toMenuPage(){
        return "menu";
    }

    @GetMapping("/menu/to-menu-update")
    public String toUpdateMenuPage(){
        return "menu_update";
    }

    @GetMapping("/task")
    public String toTaskPage(){
        return "task";
    }

    @GetMapping("/task/to-task-update")
    public String toUpdateTaskPage(){
        return "task_update";
    }

    @GetMapping("/dic")
    public String toDictionaryPage(){
        return "dictionary";
    }

    @GetMapping("/dic/to-dictionary-add")
    public String toDictionaryAdd(){
        return "dictionary_add";
    }

    @GetMapping("/dic/to-dictionary-update")
    public String toDictionaryUpdate(){
        return "dictionary_update";
    }
}
