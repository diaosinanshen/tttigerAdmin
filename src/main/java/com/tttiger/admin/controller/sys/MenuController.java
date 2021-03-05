package com.tttiger.admin.controller.sys;

import com.tttiger.admin.bean.sys.Menu;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.common.annotation.validate.Update;
import com.tttiger.admin.service.sys.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController{

    @Autowired
    private MenuService menuService;

    @GetMapping("/select-all")
    public ResultMap<List<Menu>> selectAllMenu(){
        return ResultMap.data(menuService.selectAll()).success();
    }

    @GetMapping("/select")
    public ResultMap<List<Menu>> selectMenu(){
        return menuService.selectList(null);
    }

    @PostMapping("/update")
    public ResultMap<Object> updateMenu(@RequestBody @Validated(Update.class) Menu menu){
        return menuService.updateById(menu);
    }
}
