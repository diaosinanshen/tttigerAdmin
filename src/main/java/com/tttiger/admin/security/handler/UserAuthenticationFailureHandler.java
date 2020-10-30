package com.tttiger.admin.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author QinHaoTong
 */
@Component
@Slf4j
public class UserAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        ResultMap message = ResultMap.fail().message("账号或密码不正确");
        response.getWriter().write(JSONObject.toJSONString(message));

        String ipAddress = SecurityUtil.getIPAddress(request);
        log.warn("ip：{} 尝试登录失败",ipAddress);
    }
}
