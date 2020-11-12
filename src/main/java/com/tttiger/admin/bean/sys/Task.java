package com.tttiger.admin.bean.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tttiger.admin.common.annotation.validate.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

/**
 * @author QinHaoTong
 */
@Data
public class Task {
    @TableId(type = IdType.AUTO)

    @NotNull(groups = {Update.class}, message = "id不能为空")
    private Integer id;

    @Null(groups = {Update.class}, message = "非法参数")
    private String jobName;

    @NotBlank(groups = {Update.class}, message = "计划任务描述不能为空")
    @Length(min = 2, max = 255, groups = {Update.class}, message = "任务名称长度要在2-255个字符")
    private String description;

    @NotBlank(groups = {Update.class}, message = "cron表达式不能为空")
    private String cronExpression;

    @Null(groups = {Update.class}, message = "非法参数")
    private String beanClass;

    @Null(groups = {Update.class}, message = "不能直接更新任务状态")
    private Integer jobStatus;

    @Null(groups = {Update.class}, message = "非法参数")
    private String jobGroup;

    @Null(groups = {Update.class}, message = "非法参数")
    private Date createTime;

    private Date updateTime;

    /**
     * 计划任务激活状态
     */
    @TableField(exist = false)
    public static final int ACTIVE = 1;

    /**
     * 计划任务停止状态
     */
    @TableField(exist = false)
    public static final int INACTIVE = 0;

}
