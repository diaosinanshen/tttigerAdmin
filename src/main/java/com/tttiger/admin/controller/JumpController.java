package com.tttiger.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/12/22 21:07
 * @Description
 */
@Controller
public class JumpController {

    @GetMapping("/manager")
    public String toManager(){
        return "manager";
    }
}
