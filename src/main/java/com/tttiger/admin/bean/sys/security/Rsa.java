package com.tttiger.admin.bean.sys.security;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author QinHaoTong
 * @dateTime 2020/11/17 13:56
 * @description 封装Rsa加密私钥公钥
 */
@Data
@AllArgsConstructor
public class Rsa {
    /**
     * 加密公钥
     */
    private String publicKeyString;
    /**
     * 加密私钥
     */
    private String privateKeyString;
}
