package com.tttiger.admin.bean.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tttiger.admin.bean.BaseBean;
import com.tttiger.admin.common.annotation.validate.Add;
import com.tttiger.admin.common.annotation.validate.Update;
import com.tttiger.admin.common.annotation.wrapper.AutoWrapper;
import com.tttiger.admin.common.annotation.wrapper.Eq;
import com.tttiger.admin.common.annotation.wrapper.Like;
import com.tttiger.admin.common.annotation.wrapper.group.SelectPage;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;
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
    @NotBlank(message = "管理名称不能为空",groups = {Add.class, Update.class})
    @Length(min = 2,message = "名称最少需要字符",groups = {Add.class, Update.class} )
    private String managerName;

    @AutoWrapper(value = Eq.class, group = {SelectPage.class})
    @Length(min = 6,max = 12,message = "请输入6至12位账号")
    private String managerAccount;

    @Length(min = 6,max = 12,message = "请输入6至12位密码",groups = {Add.class, Update.class})
    @NotBlank(message = "密码不能为空",groups = {Add.class, Update.class})
    private String managerPassword;

    /**
     * 账号状态
     */
    private Integer status;
    @TableField(exist = false)
    public static final Integer ACCOUNT_ACTIVE = 0;
    @TableField(exist = false)
    public static final Integer ACCOUNT_LOCKED = 1;
    @TableField(exist = false)
    public static final Integer ACCOUNT_EXPIRED = 2;


    /**
     * 账号过期时间
     */
    private Date overTime;

    /**
     * 添加管理密码确认
     */
    @TableField(exist = false)
    private String checkPassword;


    @TableField(exist = false)
    private List<Role> roles;
}
