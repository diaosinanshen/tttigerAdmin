package com.tttiger.admin.bean.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tttiger.admin.common.annotation.validate.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/29 15:27
 * @Description 访问路径
 */
@Data
public class Menu {

    @TableId(type = IdType.AUTO)
    @NotNull(message = "主键不能为null",groups = {Update.class})
    private Integer menuId;
    private String title;

    @Null(message = "主键不能为null",groups = {Update.class})
    private String href;
    private Integer parentMenu;

    @TableField(exist = false)
    private List<Menu> children;
    private String icon;

    @TableField(exist = false)
    private List<Role> roles;

    private Integer sort;
}
