package com.tttiger.admin.controller;

import com.tttiger.admin.bean.Manager;
import com.tttiger.admin.bean.Menu;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.service.ManagerService;
import com.tttiger.admin.service.MenuService;
import com.tttiger.admin.utils.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/29 15:56
 * @Description
 */
@Controller
@AllArgsConstructor
@Slf4j
public class LoginController {

    private ManagerService managerService;

    private MenuService menuService;

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
        log.info("管理员：{1}登录",authentication.getName());
        return "admin";
    }


    @RequestMapping("/selectAuthorityMenu")
    @ResponseBody
    public ResultMap selectAuthorityMenu(){
        Authentication currentUser = SecurityUtil.getCurrentUser();
        String userAccount = currentUser.getName();
        List<Menu> menus = menuService.selectUserHasAuthorityMenu(userAccount);
        return ResultMap.ok().data(menus);
    }
}
