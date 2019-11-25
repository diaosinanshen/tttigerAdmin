package com.tttiger.admin.service.iml;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tttiger.admin.bean.Role;
import com.tttiger.admin.mapper.RoleMapper;
import com.tttiger.admin.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/22 15:59
 * @Description
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleMapper roleMapper;

    @Override
    public BaseMapper<Role> getMapper() {
        return roleMapper;
    }
}
