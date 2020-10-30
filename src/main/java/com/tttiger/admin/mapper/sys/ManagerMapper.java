package com.tttiger.admin.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tttiger.admin.bean.sys.Manager;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/09/29 15:54
 * @Description
 */
public interface ManagerMapper extends BaseMapper<Manager> {

    Manager selectManagerAndRoles(String account);
}
