package com.tttiger.admin.bean.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tttiger.admin.bean.BaseBean;
import lombok.Data;

/**
 * @author QinHaoTong
 * @dateTime 2020/12/23 9:18
 * @description
 * 系统字典操作
 */
@Data
public class Dictionary extends BaseBean {

    @TableId(type = IdType.AUTO)
    private Integer dicId;

    private String moduleName;

    private String moduleDescription;

    private String groupName;

    private String groupDescription;

    private String dicKey;

    private String dicValue;

    private Integer valueType;

    private String description;
}
