package com.tttiger.admin.bean.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tttiger.admin.bean.BaseBean;
import com.tttiger.admin.common.annotation.validate.Add;
import com.tttiger.admin.common.annotation.validate.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author QinHaoTong
 * @dateTime 2020/12/23 9:18
 * @description
 * 系统字典操作
 */
@Data
public class Dictionary extends BaseBean {

    @TableId(type = IdType.AUTO)
    @NotNull(groups = {Update.class},message = "主键不能为空")
    private Integer dicId;

    @NotBlank(groups = {Update.class, Add.class},message = "字典所属模块名称不能为空")
    private String moduleName;

    private String moduleKey;


    @NotBlank(groups = {Update.class, Add.class},message = "字典所属组名称不能为空")
    private String groupName;

    private String groupKey;

    @NotBlank(groups = {Update.class, Add.class},message = "字典键不能为空")
    private String dicKey;

    @NotBlank(groups = {Update.class, Add.class},message = "字典值不能为空")
    private String dicValue;


    @NotNull(groups = {Update.class, Add.class},message = "字典值类型不能为空")
    private Integer valueType;

    private String description;
}
