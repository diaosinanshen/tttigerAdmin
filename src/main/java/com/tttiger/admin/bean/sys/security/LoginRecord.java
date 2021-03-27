package com.tttiger.admin.bean.sys.security;

import lombok.Data;

import java.util.Date;

/**
 * @author 秦浩桐
 */
@Data
public class LoginRecord {
    private Integer id;
    private String account;
    private Date loginTime;
    private Boolean loginSuccess;
    private String ip;
}
