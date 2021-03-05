package com.tttiger.admin.service.sys;

import com.tttiger.admin.bean.sys.Task;
import com.tttiger.admin.common.ResultMap;
import org.quartz.SchedulerException;

/**
 * @author QinHaoTong
 */
public interface TaskService extends BaseService<Task>{

    /**
     * 根据定时任务主键暂停定时任务
     * @param taskId 任务taskid
     * @exception SchedulerException 暂停计划任务失败
     * @return 响应实体
     */
    ResultMap<Object> pauseTask(Integer taskId) throws SchedulerException;

    /**
     * 初始化定时任务
     */
    void initSchedule() throws SchedulerException;

    /**
     * 更新计划任务
     * @param task 包含id与更新内容的实体
     * @return 响应实体
     */
    ResultMap<Object> updateTask(Task task) throws SchedulerException;


    /**
     * 恢复定时任务
     * @param taskId 任务主键id
     * @return 响应实体
     */
    ResultMap<Object> resumeTask(Integer taskId) throws SchedulerException;
}
