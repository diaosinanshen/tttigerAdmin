package com.tttiger.admin.bean.sys.security;

import lombok.Data;

/**
 * @author QinHaoTong
 */
@Data
public class IpInfo {
    private Long id;
    private String ip;
    private String province;
    private String provinceCode;
    private String city;
    private String cityCode;
    private String region;
    private String regionCode;
    private String addr;
}
