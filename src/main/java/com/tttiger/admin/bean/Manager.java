package com.tttiger.admin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tttiger.admin.common.annotation.wrapper.AutoWrapper;
import com.tttiger.admin.common.annotation.wrapper.Eq;
import com.tttiger.admin.common.annotation.wrapper.Like;
import com.tttiger.admin.common.annotation.wrapper.group.SelectPage;
import lombok.Data;

import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/29 15:26
 * @Description
 */
@Data
public class Manager extends BaseBean {

    @TableId(type = IdType.AUTO)
    private Integer managerId;

    @AutoWrapper(value = Like.class, group = {SelectPage.class})
    private String managerName;
    @AutoWrapper(value = Eq.class, group = {SelectPage.class})
    private String managerAccount;
    private String managerPassword;


    @TableField(exist = false)
    private List<Role> roles;
}
