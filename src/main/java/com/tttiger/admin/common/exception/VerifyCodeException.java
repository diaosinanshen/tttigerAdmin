package com.tttiger.admin.common.exception;

import org.springframework.security.core.AuthenticationException;

public class VerifyCodeException extends AuthenticationException {

    public VerifyCodeException(String message) {
        super(message);
    }
}
