package com.tttiger.admin.common.task;

import com.tttiger.admin.bean.sys.Task;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;

/**
 * @author QinHaoTong
 */
@Component
@Slf4j
@AllArgsConstructor
public class QuartzManager {

    private Scheduler scheduler;

    /**
     * 添加定时任务
     *
     * @param task
     */
    @SuppressWarnings("all")
    public void addJob(Task task) {
        try {
            Class<? extends Job> jobClass = (Class<? extends Job>) (Class.forName(task.getBeanClass()).newInstance()
                    .getClass());
            // 构建job信息
            // 任务名称和组构成任务key
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(task.getJobName(), task.getJobGroup())
                    .build();
            // 触发器key
            // 立即执行
            // 使用cornTrigger规则
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getJobName(), task.getJobGroup())
                    .withSchedule(CronScheduleBuilder.cronSchedule(task.getCronExpression())).startNow().build();
            // 把作业和触发器注册到任务调度中
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("创建定时任务失败", e);
        }
    }

    /**
     * 暂停定时任务
     *
     * @param task
     * @throws SchedulerException
     */
    public void pauseJob(Task task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复暂停的定时任务
     *
     * @param task
     * @throws SchedulerException
     */
    public void resumeJob(Task task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除定时任务
     * @param task
     * @throws SchedulerException
     */
    public void deleteJob(Task task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.deleteJob(jobKey);
    }

    /**
     * 更新定时任务表达式
     * @param task
     * @throws SchedulerException
     */
    public void updateJobCron(Task task) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(task.getJobName(), task.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCronExpression());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     * 立即触发定时任务
     *
     * @param task
     * @throws SchedulerException
     */
    public void runJobNow(Task task) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 是否包含计划任务
     *
     * @param task
     * @return
     */
    public boolean containJob(Task task) {
        JobKey jobKey = JobKey.jobKey(task.getJobName(), task.getJobGroup());
        JobDetail jobDetail = null;
        try {
            jobDetail = scheduler.getJobDetail(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobDetail != null;
    }

    public void startScheduler() throws SchedulerException {
        scheduler.start();
    }
}
