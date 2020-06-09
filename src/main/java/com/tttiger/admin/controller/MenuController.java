package com.tttiger.admin.controller;

import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/select-all")
    public ResultMap selectAllMenu(){
        return ResultMap.success().data(menuService.selectAll());
    }

}
