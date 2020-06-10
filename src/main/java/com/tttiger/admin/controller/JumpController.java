package com.tttiger.admin.controller;

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

    @GetMapping("/manager")
    public String toManager(){
        return "manager";
    }
}
