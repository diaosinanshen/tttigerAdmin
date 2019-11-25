package com.tttiger.admin.controller;

import com.tttiger.admin.bean.Manager;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/29 15:56
 * @Description
 */
@Controller
@AllArgsConstructor
public class LoginController {

    private ManagerService managerService;

    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/loginFail")
    public String test(){
        System.out.println("登录失败");
        return "login";
    }

    @RequestMapping("/loginCheck")
    public String test(Manager manager) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken){
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(manager.getManagerAccount(), manager.getManagerPassword()));
        }

        return "index";
    }

    @RequestMapping("/denied")
    public String test4(){
        return "denied";
    }

    @RequestMapping("/hao")
    @ResponseBody
    public ResultMap test3(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("需要登录");
        return ResultMap.ok();
    }


    @RequestMapping("/qing")
    @ResponseBody
    public ResultMap test2(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("需要登录");
        return ResultMap.ok();
    }
}
