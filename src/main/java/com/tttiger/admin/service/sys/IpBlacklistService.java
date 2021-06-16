package com.tttiger.admin.service.sys;

import com.tttiger.admin.bean.sys.security.IpBlacklist;
import com.tttiger.admin.common.ResultMap;

/**
 * @author QinHaoTong
 * @dateTime 2020/11/18 16:46
 * @description
 */
public interface IpBlacklistService extends BaseService<IpBlacklist> {

    /**
     * 添加ip到黑名单
     * @param ipAddress ip地址
     * @return 响应结果封装
     */
    ResultMap<Object> insert(String ipAddress);
}
