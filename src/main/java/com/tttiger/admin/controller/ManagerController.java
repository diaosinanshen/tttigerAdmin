package com.tttiger.admin.controller;

import com.tttiger.admin.bean.Manager;
import com.tttiger.admin.controller.base.BaseCrudController;
import com.tttiger.admin.service.BaseService;
import com.tttiger.admin.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/22 16:04
 * @Description
 */
@Controller
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController implements BaseCrudController<Manager> {

    private ManagerService managerService;


    @GetMapping("/toManager")
    public String toAddManager(){
        return "manager";
    }


    @Override
    public BaseService<Manager> getService() {
        return managerService;
    }
}
