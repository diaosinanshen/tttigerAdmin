package com.tttiger.admin.service.sys.iml;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tttiger.admin.bean.sys.security.IpBlacklist;
import com.tttiger.admin.mapper.sys.IpBlacklistMapper;
import com.tttiger.admin.service.sys.IpBlacklistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author QinHaoTong
 * @dateTime 2020/11/18 16:46
 * @description
 */
@Service
@AllArgsConstructor
public class IpBlacklistServiceImpl implements IpBlacklistService {

    private IpBlacklistMapper ipAddressMapper;

    @Override
    public BaseMapper<IpBlacklist> getMapper() {
        return ipAddressMapper;
    }
}
