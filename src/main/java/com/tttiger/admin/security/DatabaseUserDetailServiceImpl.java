package com.tttiger.admin.security;

import com.tttiger.admin.bean.sys.Manager;
import com.tttiger.admin.mapper.sys.ManagerMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/10 17:46
 * @Description
 */
@Component
@AllArgsConstructor
public class DatabaseUserDetailServiceImpl implements UserDetailsService {

    private ManagerMapper managerMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = managerMapper.selectManagerAndRoles(username);

        // 判断用户状态
        if (manager == null) {
            throw new UsernameNotFoundException("账号密码不正确");
        }else if(Manager.ACCOUNT_LOCKED.equals(manager.getStatus())){
            throw new LockedException("账号以被锁定");
        }else if(Manager.ACCOUNT_EXPIRED.equals(manager.getStatus())){
            throw new AccountExpiredException("账号以过期");
        }

        // 查看账号是否过期
        if (manager.getOverTime()!= null && manager.getOverTime().before(new Date())) {
            Manager lockManger = new Manager();
            lockManger.setManagerId(manager.getManagerId());
            lockManger.setStatus(Manager.ACCOUNT_EXPIRED);
            managerMapper.updateById(lockManger);
            throw new AccountExpiredException("账号以过期");
        }

        // 添加用户角色
        List<GrantedAuthority> list = new ArrayList<>();
        manager.getRoles().forEach(x -> list.add(new SimpleGrantedAuthority("ROLE_"+x.getRoleName())));
        return new User(manager.getManagerAccount(), manager.getManagerPassword(), list);
    }
}
