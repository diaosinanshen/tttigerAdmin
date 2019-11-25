package com.tttiger.admin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/29 15:27
 * @Description 访问路径
 */
@Data
public class Menu extends BaseBean {
    @TableId(type = IdType.AUTO)
    private Integer menuId;
    private String url;
    private String menuName;
    private Integer parentMenu;

    @TableField(exist = false)
    private List<Role> roles;
}
