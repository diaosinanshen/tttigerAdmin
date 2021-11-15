package com.tttiger.admin.security;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author QinHaoTong
 * @dateTime 2021/6/24 14:56
 * @description
 */
public class RedirectSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("<script>alert('登录已经失效或账号在别处登录')</script>");
        response.flushBuffer();
    }
}
