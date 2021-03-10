package com.tttiger.admin.bean.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tttiger.admin.bean.BaseBean;
import com.tttiger.admin.common.annotation.validate.Update;
import lombok.Data;

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

    private String moduleName;

    private String groupName;

    private String dicKey;

    private String dicValue;

    private Integer valueType;

    private String description;
}
