package com.tttiger.admin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tttiger.admin.common.annotation.security.SecurityParameter;
import com.tttiger.admin.utils.AesUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author QinHaoTong
 * @dateTime 2020/11/13 10:27
 * @description
 */
@ControllerAdvice(basePackages = "com.tttiger.admin.controller")
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        boolean encode = false;
        if (methodParameter.getMethod().isAnnotationPresent(SecurityParameter.class)) {
            //获取注解配置的包含和去除字段
            SecurityParameter serializedField = methodParameter.getMethodAnnotation(SecurityParameter.class);
            //出参是否需要加密
            encode = serializedField.encrypt();
        }
        if (encode) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String aesKey = httpServletRequest.getSession().getAttribute("transportAesKey").toString();
                String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
                return AesUtil.encrypt(result, aesKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return body;
    }
}
