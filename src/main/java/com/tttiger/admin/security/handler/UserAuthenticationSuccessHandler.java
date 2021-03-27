package com.tttiger.admin.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.tttiger.admin.bean.sys.Dictionary;
import com.tttiger.admin.bean.sys.security.IpBlacklist;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.service.sys.ApplicationConfigService;
import com.tttiger.admin.service.sys.IpBlacklistService;
import com.tttiger.admin.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Component
@Slf4j
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private IpBlacklistService ipBlacklistService;

    @Autowired
    private ApplicationConfigService applicationConfigService;

    @Autowired
    private SessionRegistry sessionRegistry;

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

        // 单点登陆
        Dictionary singleLogin = applicationConfigService.getSingleConfig("系统管理", "标识", "single_login");
        if(Boolean.parseBoolean(singleLogin.getDicValue())){
            List<SessionInformation> allSessions = sessionRegistry.getAllSessions(authentication.getPrincipal(), false);
            System.out.println(allSessions.size());
        }


        log.info(authentication.getName()+"登录");
        returnMessage(response,ResultMap.data().success().message("登陆成功"));
    }

    private void returnMessage( HttpServletResponse response,ResultMap<Object> resultMap) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().write(JSONObject.toJSONString(resultMap));
    }
}
