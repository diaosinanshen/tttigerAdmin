package com.tttiger.admin.service.sys;

import com.tttiger.admin.bean.sys.Dictionary;

import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @date 2021/03/10 20:12
 */
public interface ApplicationConfigService  {

    /**
     * 获取应用字典配置
     * @param moduleName 字典模块名称
     * @param groupName 字典组名称
     * @param dicKey 字典key
     * @return 可能是多个的字典集合
     */
    List<Dictionary> getConfig(String moduleName,String groupName,String dicKey);

    /**
     * 获取应用确定返回一个字典配置
     * @param moduleName 字典模块名称
     * @param groupName 字典组名称
     * @param dicKey 字典key
     * @return 字典
     */
    Dictionary getSingleConfig(String moduleName,String groupName,String dicKey);

    /**
     * 重新加载字典配置
     */
    void reloadConfig();
}
