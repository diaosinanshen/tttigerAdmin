package com.tttiger.admin.security;

import com.tttiger.admin.bean.Manager;
import com.tttiger.admin.mapper.ManagerMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        if (manager == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> list = new ArrayList<>();
        manager.getRoles().forEach(x -> list.add(new SimpleGrantedAuthority("ROLE_"+x.getRoleName())));
        return new User(manager.getManagerAccount(), manager.getManagerPassword(), list);
    }
}
