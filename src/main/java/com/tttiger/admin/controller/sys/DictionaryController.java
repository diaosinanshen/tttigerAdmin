package com.tttiger.admin.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tttiger.admin.bean.sys.Dictionary;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.common.annotation.security.SecurityParameter;
import com.tttiger.admin.common.annotation.validate.Update;
import com.tttiger.admin.controller.base.BaseDeleteController;
import com.tttiger.admin.service.sys.ApplicationConfigService;
import com.tttiger.admin.service.sys.BaseService;
import com.tttiger.admin.service.sys.DictionaryService;
import com.tttiger.admin.utils.StringUtil;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author QinHaoTong
 * @dateTime 2020/12/23 9:17
 * @description 系统字典操作
 */
@RestController
@RequestMapping("/dic")
@AllArgsConstructor
public class DictionaryController implements BaseDeleteController<Dictionary> {

    private DictionaryService dictionaryService;

    private ApplicationConfigService applicationConfigService;

    @SecurityParameter
    @PostMapping("/add")
    public ResultMap<Object> add(@RequestBody Dictionary dictionary) {
        Date cur = new Date();
        dictionary.setUpdateTime(cur);
        dictionary.setCreateTime(cur);
        if (dictionaryService.insert(dictionary).isSuccess()) {
            applicationConfigService.reloadConfig();
            return ResultMap.data().success().message("添加成功");
        }
        return ResultMap.data().fail().message("添加失败");
    }

    @SecurityParameter
    @PostMapping("/update")
    public ResultMap<Object> update(@Validated({Update.class}) @RequestBody Dictionary dictionary) {
        Date cur = new Date();
        dictionary.setUpdateTime(cur);
        if (dictionaryService.updateById(dictionary).isSuccess()) {
            applicationConfigService.reloadConfig();
            return ResultMap.data().success().message("更新成功");
        }
        return ResultMap.data().fail().message("更新失败");
    }


    /**
     * 通用实体分页查询
     *
     * @param page  查询页码
     * @param limit 分页大小
     * @return 统一结果封装
     */
    @GetMapping("/select")
    @SecurityParameter(decrypt = false)
    public ResultMap<IPage<Dictionary>> select(@RequestParam(required = false, defaultValue = "1", value = "page") Integer page,
                                               @RequestParam(required = false, defaultValue = "10", value = "limit") Integer limit,
                                               String moduleName,String groupName, String dicKey) {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<Dictionary> lambda = queryWrapper.lambda();
        lambda.eq(StringUtil.isNotEmpty(moduleName),Dictionary::getModuleName,moduleName);
        lambda.eq(StringUtil.isNotEmpty(groupName),Dictionary::getGroupName,groupName);
        lambda.eq(StringUtil.isNotEmpty(dicKey),Dictionary::getDicKey,dicKey);
        return dictionaryService.selectPage(new Page<>(page, limit), queryWrapper);
    }


    @GetMapping("/all-module")
    public ResultMap<List<Dictionary>> selectAllModule() {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("DISTINCT module_name");
        return dictionaryService.selectList(queryWrapper);
    }

    @GetMapping("/all-group")
    public ResultMap<List<Dictionary>> selectAllGroup(String moduleName) {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Dictionary::getModuleName,moduleName);
        queryWrapper.select("DISTINCT group_name");
        return dictionaryService.selectList(queryWrapper);
    }

    @Override
    public BaseService<Dictionary> getService() {
        return dictionaryService;
    }
}
