package com.tttiger.admin.service.sys.iml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tttiger.admin.bean.sys.Manager;
import com.tttiger.admin.bean.sys.ManagerRole;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.mapper.sys.ManagerMapper;
import com.tttiger.admin.mapper.sys.ManagerRoleMapper;
import com.tttiger.admin.security.RoleBasedVoter;
import com.tttiger.admin.service.sys.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/04 17:14
 * @Description
 */
@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private ManagerMapper managerMapper;

    private ManagerRoleMapper managerRoleMapper;

    private RoleBasedVoter roleBasedVoter;

    @Override
    public BaseMapper<Manager> getMapper() {
        return managerMapper;
    }

    @Override
    public ResultMap selectAuthRole(String managerAccount){
        Manager manager = managerMapper.selectManagerAndRoles(managerAccount);
        return ResultMap.success().data(manager.getRoles());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultMap auth(Manager manager) {
        Integer managerId = manager.getManagerId();

        QueryWrapper<ManagerRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ManagerRole::getManagerId,managerId);
        // 清空之前的权限
        managerRoleMapper.delete(wrapper);
        if(manager.getRoles() != null){
            Date cur = new Date();
            manager.getRoles().forEach(x->{
                ManagerRole managerRole = new ManagerRole();
                managerRole.setManagerId(managerId);
                managerRole.setRoleId(x.getRoleId());
                managerRole.setCreateTime(cur);
                managerRole.setUpdateTime(cur);
                managerRoleMapper.insert(managerRole);
            });
        }
        roleBasedVoter.reloadAuthority();
        return ResultMap.success().message("授权成功");
    }
}
