package com.tttiger.admin.security;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tttiger.admin.utils.AesUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author QinHaoTong
 * @dateTime 2020/11/15 11:08
 * @description 自定义登录过滤器实现json提交登录信息登录
 */
public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {

            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream is = request.getInputStream()) {
                ServletInputStream inputStream = request.getInputStream();
                // 解密json信息
                String aesKey = request.getSession().getAttribute("transportAesKey").toString();
                String aesIv = request.getSession().getAttribute("transportAesIv").toString();
                String decrypt = AesUtil.decrypt(IOUtils.toString(inputStream, "UTF-8"), aesKey,aesIv);
                Map<Object,Object> map = JSON.parseObject(decrypt, Map.class);

                authRequest = new UsernamePasswordAuthenticationToken(
                        map.get(getUsernameParameter()), map.get(getPasswordParameter()));
            } catch (IOException e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken(
                        "", "");
            } finally {
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }
        else {
            return super.attemptAuthentication(request, response);
        }
    }

}