package com.tttiger.admin.bean.sys.security;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author 秦浩桐
 */
@Data
public class LoginRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String account;
    private Date loginTime;
    private Boolean loginSuccess;
    private String ip;
}
