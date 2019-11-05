package com.tttiger.admin.service.iml;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tttiger.admin.bean.Manager;
import com.tttiger.admin.mapper.ManagerMapper;
import com.tttiger.admin.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public BaseMapper<Manager> getMapper() {
        return managerMapper;
    }
}
