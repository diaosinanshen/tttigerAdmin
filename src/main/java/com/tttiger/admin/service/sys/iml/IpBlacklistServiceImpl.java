package com.tttiger.admin.service.sys.iml;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tttiger.admin.bean.sys.security.IpBlacklist;
import com.tttiger.admin.bean.sys.security.IpInfo;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.mapper.sys.IpBlacklistMapper;
import com.tttiger.admin.service.sys.IpBlacklistService;
import com.tttiger.admin.utils.IpUtil;
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

    private IpBlacklistMapper ipBlacklistMapper;

    @Override
    public BaseMapper<IpBlacklist> getMapper() {
        return ipBlacklistMapper;
    }

    @Override
    public ResultMap<Object> insert(String ipAddress) {
        IpInfo ipInfo = IpUtil.getIpInfo(ipAddress);
        IpBlacklist ip = new IpBlacklist();
        ip.setProvince(ipInfo.getProvince());
        ip.setProvinceCode(ipInfo.getProvinceCode());
        ip.setCity(ipInfo.getCity());
        ip.setCityCode(ipInfo.getCityCode());
        ip.setIp(ipAddress);
        ip.setRegion(ipInfo.getRegion());
        ip.setRegionCode(ipInfo.getRegionCode());
        return ResultMap.data().predict(ipBlacklistMapper.insert(ip) == 1);
    }
}
