package com.tttiger.admin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/29 15:26
 * @Description
 */
@Data
public class Manager {

    @TableId(type = IdType.AUTO)
    private Integer managerId;
    private String managerName;
    private String managerAccount;
    private String managerPassword;

    private Date createTime;
    private Date updateTime;


    @TableField(exist = false)
    private List<Role> roles;
}
