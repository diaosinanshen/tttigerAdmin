package com.tttiger.admin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tttiger.admin.common.annotation.validate.Add;
import com.tttiger.admin.common.annotation.validate.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/29 15:27
 * @Description 账号角色
 */
@Data
public class Role extends BaseBean {

    @TableId(type = IdType.AUTO)
    @Null(groups = {Add.class},message = "参数格式错误")
    @NotNull(groups = {Update.class},message = "参数格式错误")
    private Integer roleId;

    @NotBlank(groups = {Add.class,Update.class}, message = "角色名称不能为空")
    private String roleName;

    @TableField(exist = false)

    @Size(groups = {Add.class,Update.class}, min = 1, message = "角色权限不能为空")
    private List<Menu> menus;
}
