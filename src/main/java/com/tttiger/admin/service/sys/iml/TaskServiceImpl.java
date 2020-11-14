package com.tttiger.admin.service.sys.iml;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tttiger.admin.bean.sys.Task;
import com.tttiger.admin.common.task.QuartzManager;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.mapper.sys.TaskMapper;
import com.tttiger.admin.service.sys.TaskService;
import lombok.AllArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author QinHaoTong
 */
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private TaskMapper taskMapper;

    private QuartzManager quartzManager;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultMap pauseTask(Integer taskId) throws SchedulerException {
        Task pauseTask = taskMapper.selectById(taskId);
        // 本身是暂停状态，直接返回成功
        if(pauseTask.getJobStatus().equals(Task.INACTIVE)){
            return ResultMap.success();
        }
        Task task = new Task();
        task.setId(taskId);
        task.setJobStatus(Task.INACTIVE);
        if(taskMapper.updateById(task) == 1){
            // 直接删除任务
            quartzManager.deleteJob(pauseTask);
            return ResultMap.success();
        }
        return ResultMap.fail();
    }



    @Override
    public void initSchedule() throws SchedulerException {
        List<Task> tasks = taskMapper.selectList(null);
        // 已经暂停的计划任务在初始化时是不会添加
        tasks.stream().filter(x->x.getJobStatus().equals(Task.ACTIVE)).forEach(quartzManager::addJob);
    }

    @Override
    public ResultMap updateTask(Task task) throws SchedulerException {
        Task updateTask = taskMapper.selectById(task.getId());
        if(updateTask.getJobStatus().equals(Task.INACTIVE)){
            if(taskMapper.updateById(task) == 1){
                return ResultMap.success();
            }
            return ResultMap.fail().message("请先暂停任务");
        }else if(updateTask.getJobStatus().equals(Task.ACTIVE)){
            if (taskMapper.updateById(task) == 1) {
                updateTask = taskMapper.selectById(task.getId());
                quartzManager.updateJobCron(updateTask);
                return ResultMap.success();
            }
        }
        return ResultMap.fail();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultMap resumeTask(Integer taskId) throws SchedulerException {
        Task task = taskMapper.selectById(taskId);
        if(task.getJobStatus().equals(Task.INACTIVE)){
            Task resumeTask = new Task();
            resumeTask.setId(taskId);
            resumeTask.setJobStatus(Task.ACTIVE);
            if(taskMapper.updateById(resumeTask) == 1){
                // 添加更新后的任务
                task = taskMapper.selectById(taskId);
                quartzManager.addJob(task);
                return ResultMap.success();
            }
        }
        return ResultMap.fail();
    }

    @Override
    public BaseMapper<Task> getMapper() {
        return taskMapper;
    }
}
