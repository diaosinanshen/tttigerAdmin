package com.tttiger.admin.bean.sys.security;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author QinHaoTong
 * @dateTime 2020/11/17 13:46
 * @description
 */
@Data
public class IpBlacklist {
    @TableId(type = IdType.INPUT)
    private Long id;
    private String ip;
    private String province;
    private String provinceCode;
    private String city;
    private String cityCode;
    private String region;
    private String regionCode;
    private String address;
}
