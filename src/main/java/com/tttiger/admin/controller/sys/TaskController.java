package com.tttiger.admin.controller.sys;


import com.tttiger.admin.bean.sys.Task;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.common.annotation.validate.CommonValid;
import com.tttiger.admin.common.annotation.validate.Update;
import com.tttiger.admin.controller.base.BaseSelectPageController;
import com.tttiger.admin.service.sys.BaseService;
import com.tttiger.admin.service.sys.TaskService;
import lombok.AllArgsConstructor;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author QinHaoTong
 */
@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController implements BaseSelectPageController<Task> {

    private TaskService taskService;

    @CommonValid
    @PostMapping("/pause")
    public ResultMap pauseTask(@NotNull(message = "定时任务ID缺失") Integer taskId) throws SchedulerException {

        return taskService.pauseTask(taskId);
    }

    @CommonValid
    @PostMapping("/resume")
    public ResultMap resumeTask(@NotNull(message = "定时任务ID缺失") Integer taskId) throws SchedulerException {
        return taskService.resumeTask(taskId);
    }

    @PostMapping("/update")
    public ResultMap updateTask(@Validated(Update.class) @RequestBody Task task) throws SchedulerException {
        if (!CronExpression.isValidExpression(task.getCronExpression())) {
            return ResultMap.fail().message("cron表达式错误");
        }
        task.setUpdateTime(new Date());
        return taskService.updateTask(task);
    }



    @Override
    public BaseService<Task> getService() {
        return taskService;
    }
}
