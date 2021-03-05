package com.tttiger.admin.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tttiger.admin.bean.sys.Manager;
import com.tttiger.admin.bean.sys.ManagerChangePwdDTO;
import com.tttiger.admin.bean.sys.Role;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.common.annotation.security.SecurityParameter;
import com.tttiger.admin.common.annotation.validate.Add;
import com.tttiger.admin.common.annotation.validate.CommonValid;
import com.tttiger.admin.controller.base.BaseCrudController;
import com.tttiger.admin.service.sys.BaseService;
import com.tttiger.admin.service.sys.ManagerService;
import com.tttiger.admin.service.sys.RoleService;
import com.tttiger.admin.utils.SecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/22 16:04
 * @Description
 */
@RestController
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController implements BaseCrudController<Manager> {

    private ManagerService managerService;

    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    @Override
    @SecurityParameter
    public ResultMap<Object> add(@RequestBody @Validated(Add.class) Manager manager) {
        if(manager.getManagerPassword().equals(manager.getCheckPassword())){
            QueryWrapper<Manager> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(Manager::getManagerAccount,manager.getManagerAccount());
            ResultMap<Manager> resultMap = managerService.selectOne(wrapper);
            if(!resultMap.isEmpty()){
                return ResultMap.data().fail().message("账号冲突");
            }
            return managerService.insert(manager);
        }else{
            return ResultMap.data().fail().message("输入密码不一致");
        }
    }

    @GetMapping("/select-role")
    public ResultMap<List<Role>> getRole(){
        return roleService.selectList(null);
    }

    /**
     * 查询管理拥有的角色
     */
    @GetMapping("/select/authority")
    @CommonValid
    @SecurityParameter(decrypt = false)
    public ResultMap<List<Role>> selectManagerRole(@NotBlank(message = "非法参数") String managerAccount){
        return managerService.selectAuthRole(managerAccount);
    }


    /**
     * 管理角色赋权
     * @param manager
     * @return
     */
    @PostMapping("/auth")
    public ResultMap<Object> auth(@RequestBody Manager manager){
        if(manager.getManagerId() == null){
            return ResultMap.data().fail().message("管理人员不存在");
        }
        return managerService.auth(manager);
    }

    /**
     * 修改当前用户登录密码
     * @param managerChangePwdDTO 管理新密码传输实体
     * @return
     */
    @PostMapping("/auth2")
    public ResultMap<Object> changePassword(ManagerChangePwdDTO managerChangePwdDTO){
        Authentication currentUser = SecurityUtil.getCurrentUser();
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Manager::getManagerAccount,currentUser.getName());
        ResultMap<Manager> resultMap = managerService.selectOne(wrapper);
        String oldPwd = passwordEncoder.encode(managerChangePwdDTO.getOldPwd());
        if(!oldPwd.equals(resultMap.getData().getManagerPassword())){
            return ResultMap.data().fail("原始密码不正确");
        }


        return ResultMap.data().fail();
    }


    @Override
    public BaseService<Manager> getService() {
        return managerService;
    }
}
