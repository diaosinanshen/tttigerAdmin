package com.tttiger.admin.bean.sys;

import lombok.Data;

/**
 * @author QinHaoTong
 * @dateTime 2020/11/20 10:44
 * @description 接收管理修改密码
 */
@Data
public class ManagerChangePwdDTO {
    private String oldPwd;
    private String newPwd;
    private String confirmNewPwd;
}
