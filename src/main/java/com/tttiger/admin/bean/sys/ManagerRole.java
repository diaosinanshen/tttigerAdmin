package com.tttiger.admin.bean.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class ManagerRole {
    @TableId(type = IdType.AUTO)
    private Integer mrId;
    private Integer roleId;
    private Integer managerId;

    private Date createTime;
    private Date updateTime;
}
