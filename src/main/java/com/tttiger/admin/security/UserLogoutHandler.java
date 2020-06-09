package com.tttiger.admin.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if( authentication.getPrincipal() instanceof UserDetails){
            UserDetails principal = (UserDetails)authentication.getPrincipal();
            authentication.getDetails();
            System.out.println(principal.getUsername()+"退出登录");
        }
    }
}
