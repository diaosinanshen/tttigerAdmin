package com.tttiger.admin.service.sys.iml;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tttiger.admin.bean.sys.security.LoginRecord;
import com.tttiger.admin.mapper.sys.LoginRecordMapper;
import com.tttiger.admin.service.sys.LoginRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author QinHaoTong
 * @dateTime 2021/4/6 14:03
 * @description
 */
@Service
@AllArgsConstructor
public class LoginRecordServiceImpl implements LoginRecordService {

    private LoginRecordMapper loginRecordMapper;

    @Override
    public BaseMapper<LoginRecord> getMapper() {
        return loginRecordMapper;
    }
}
