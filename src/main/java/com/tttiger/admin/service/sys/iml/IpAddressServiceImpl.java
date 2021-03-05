package com.tttiger.admin.service.sys.iml;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tttiger.admin.bean.sys.security.IpAddress;
import com.tttiger.admin.mapper.sys.IpAddressMapper;
import com.tttiger.admin.service.sys.IpAddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author QinHaoTong
 * @dateTime 2020/11/18 16:46
 * @description
 */
@Service
@AllArgsConstructor
public class IpAddressServiceImpl implements IpAddressService {

    private IpAddressMapper ipAddressMapper;

    @Override
    public BaseMapper<IpAddress> getMapper() {
        return ipAddressMapper;
    }
}
