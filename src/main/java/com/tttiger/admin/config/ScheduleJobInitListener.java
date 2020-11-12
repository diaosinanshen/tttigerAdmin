package com.tttiger.admin.config;


import com.tttiger.admin.service.sys.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author QinHaoTong
 */
@Component
@Order(value = 1)
@AllArgsConstructor
public class ScheduleJobInitListener implements CommandLineRunner {

    private TaskService taskService;

    @Override
    public void run(String... args) throws Exception {
        try {
            taskService.initSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
