package com.tttiger.admin.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.tttiger.admin.common.ResultMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Slf4j
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 登录成功后返回
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        ResultMap message = ResultMap.success().message("登录成功");
        response.getWriter().write(JSONObject.toJSONString(message));
        log.info(authentication.getName()+"退出登录");
    }
}
