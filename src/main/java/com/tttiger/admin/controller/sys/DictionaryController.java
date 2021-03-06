package com.tttiger.admin.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tttiger.admin.bean.sys.Dictionary;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.service.sys.DictionaryService;
import com.tttiger.admin.utils.StringUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author QinHaoTong
 * @dateTime 2020/12/23 9:17
 * @description 系统字典操作
 */
@RestController
@RequestMapping("/dic")
@AllArgsConstructor
public class DictionaryController{

    private DictionaryService dictionaryService;

    /**
     * 通用实体分页查询
     *
     * @param page  查询页码
     * @param limit 分页大小
     * @return 统一结果封装
     */
    @GetMapping("/select")
    public ResultMap<IPage<Dictionary>> select(@RequestParam(required = false, defaultValue = "1", value = "page") Integer page,
                                               @RequestParam(required = false, defaultValue = "10", value = "limit") Integer limit,
                                               String moduleName,String groupName, String dicKey) {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<Dictionary> lambda = queryWrapper.lambda();
        if(StringUtil.isNotEmpty(moduleName)){
            lambda.eq(Dictionary::getModuleName,moduleName);
        }
        if(StringUtil.isNotEmpty(groupName)){
            lambda.eq(Dictionary::getGroupName,groupName);
        }
        if(StringUtil.isNotEmpty(dicKey)){
            lambda.eq(Dictionary::getDicKey,dicKey);
        }
        return dictionaryService.selectPage(new Page<>(page, limit), queryWrapper);
    }


    @GetMapping("/all-module")
    public ResultMap<List<Dictionary>> selectAllModule() {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT module_name,module_description");
        return dictionaryService.selectList(queryWrapper);
    }

    @GetMapping("/all-group")
    public ResultMap<List<Dictionary>> selectAllGroup(String moduleName) {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Dictionary::getModuleName,moduleName);
        queryWrapper.select("DISTINCT group_name,group_description");
        return dictionaryService.selectList(queryWrapper);
    }

}
