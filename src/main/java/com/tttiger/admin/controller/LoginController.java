package com.tttiger.admin.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.tttiger.admin.bean.Manager;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.common.annotation.wrapper.group.SelectPage;
import com.tttiger.admin.service.ManagerService;
import com.tttiger.admin.utils.WrapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/29 15:56
 * @Description
 */
@RestController
@AllArgsConstructor
public class LoginController {

    private ManagerService managerService;

    @RequestMapping("/test")
    public ResultMap test(Manager manager) {
        Wrapper<Manager> wrapper = WrapperUtil.getWrapper(manager, SelectPage.class);
        return managerService.selectList(wrapper);
    }
}
