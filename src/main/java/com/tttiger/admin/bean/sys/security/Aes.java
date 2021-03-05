package com.tttiger.admin.bean.sys.security;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author QinHaoTong
 * @dateTime 2020/11/17 14:10
 * @description Aes 对称加密封装
 */
@Data
@AllArgsConstructor
public class Aes {
    /**
     * 对称加密key
     */
    private String key;
    /**
     * 对称加密偏移量
     */
    private String iv;
}
