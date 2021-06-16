package com.tttiger.admin.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tttiger.admin.bean.sys.security.LoginRecord;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.service.sys.IpBlacklistService;
import com.tttiger.admin.service.sys.LoginRecordService;
import com.tttiger.admin.utils.IpUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author QinHaoTong
 */
@Component
@Slf4j
@AllArgsConstructor
public class UserAuthenticationFailureHandler implements AuthenticationFailureHandler, ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private LoginRecordService loginRecordService;

    private IpBlacklistService ipBlacklistService;

    private final int LOGIN_FAILURE_THRESHOLD = 10;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        ResultMap message = ResultMap.data().fail().message(exception.getMessage());
        response.getWriter().write(JSONObject.toJSONString(message));
        String ipAddress = IpUtil.getIpAddr(request);
        log.warn("ip：{} 登录失败，原因：{}",ipAddress,exception.getMessage());
    }


    /**
     * @param event 账号密码错误登录失败事件监听
     */
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        String ipAddress = IpUtil.getIpAddr(servletRequestAttributes.getRequest());
        // 添加失败记录纪录
        LoginRecord record = new LoginRecord();
        record.setAccount(event.getAuthentication().getName());
        record.setLoginSuccess(false);
        record.setIp(ipAddress);
        record.setLoginTime(new Date());
        loginRecordService.insert(record);
        log.warn("ip：{} 尝试登录失败，账号：{}",ipAddress,event.getAuthentication().getName());

        // 三分钟内错误尝试9次将被封禁ip,可能遭到暴力破解
        Date cur =  new Date();
        QueryWrapper<LoginRecord> wrapper = new QueryWrapper<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cur);
        calendar.add(Calendar.MINUTE,-3);
        wrapper.lambda()
                .between(LoginRecord::getLoginTime,calendar.getTime(),cur)
                .eq(LoginRecord::getLoginSuccess,false);
        ResultMap<List<LoginRecord>> resultMap = loginRecordService.selectList(wrapper);
        if(resultMap.getData().size() > LOGIN_FAILURE_THRESHOLD){
            ipBlacklistService.insert(ipAddress);
        }
    }
}
