package com.tttiger.admin.service.sys;

import com.tttiger.admin.bean.sys.Manager;
import com.tttiger.admin.bean.sys.Role;
import com.tttiger.admin.common.ResultMap;

import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/04 17:13
 * @Description
 */
public interface ManagerService extends BaseService<Manager> {

    /**
     * 查询管理拥有的角色
     * @param account 管理账号
     * @return 响应实体
     */
    ResultMap<List<Role>>  selectAuthRole(String account);

    /**
     * 更新管理人员角色权限
     * @param manager 包含更新角色的管理人员
     * @return 响应实体
     */
    ResultMap<Object> auth(Manager manager);
}
