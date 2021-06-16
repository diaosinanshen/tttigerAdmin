package com.tttiger.admin.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.tttiger.admin.bean.sys.security.IpBlacklist;
import com.tttiger.admin.bean.sys.security.LoginRecord;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.service.sys.ApplicationConfigService;
import com.tttiger.admin.service.sys.IpBlacklistService;
import com.tttiger.admin.service.sys.LoginRecordService;
import com.tttiger.admin.utils.IpUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * @author QinHaoTong
 */
@Component
@Slf4j
@AllArgsConstructor
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private IpBlacklistService ipBlacklistService;

    private ApplicationConfigService applicationConfigService;

    private LoginRecordService loginRecordService;
    /**
     * 登录成功后返回
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        //ip黑名单验证
        String ipAddr = IpUtil.getIpAddr(request);
        ResultMap<List<IpBlacklist>> listResultMap = ipBlacklistService.selectList(null);
        if(StringUtils.isEmpty(ipAddr)){
            returnMessage(response,ResultMap.data().fail().message("ip已被禁止登陆"));
        }else{
            for(IpBlacklist ip: listResultMap.getData()){
                if(ip.getIp().equals(ipAddr)){
                    returnMessage(response,ResultMap.data().fail().message("ip已被禁止登陆"));
                }
            }
        }

        // 添加登陆纪录
        LoginRecord record = new LoginRecord();
        record.setAccount(authentication.getName());
        record.setLoginSuccess(true);
        record.setIp(ipAddr);
        record.setLoginTime(new Date());
        loginRecordService.insert(record);
        returnMessage(response,ResultMap.data().success().message("登陆成功"));
        log.info("ip：{} 账号：{} 登录",ipAddr,authentication.getName());
    }

    private void returnMessage( HttpServletResponse response,ResultMap<Object> resultMap) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().write(JSONObject.toJSONString(resultMap));
    }
}
