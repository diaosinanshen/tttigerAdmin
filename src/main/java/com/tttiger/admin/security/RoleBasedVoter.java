package com.tttiger.admin.security;

import com.tttiger.admin.bean.sys.Menu;
import com.tttiger.admin.bean.sys.Role;
import com.tttiger.admin.mapper.sys.MenuMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.*;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/13 16:04
 * @Description
 */
@Component
public class RoleBasedVoter implements AccessDecisionVoter<Object>, InitializingBean {

    private Map<String, Collection<ConfigAttribute>> urlRequiredRoleMapping = new HashMap<>();

    @Autowired
    private MenuMapper menuMapper;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public boolean supports(ConfigAttribute attribute) {
        if ((attribute.getAttribute() != null)
                && attribute.getAttribute().startsWith("ROLE_")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        if (authentication == null) {
            return ACCESS_DENIED;
        }

        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();

        Collection<ConfigAttribute> dataBaseConfigAttributes = getConfigAttribute(url);

        int result = ACCESS_ABSTAIN;

        // 配置类中的权限校验
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            for (ConfigAttribute var1 : attributes) {
                if (attributes.contains(grantedAuthority)) {
                    result = ACCESS_GRANTED;
                }
            }
        }
        // 数据库中的选项校验
        if (dataBaseConfigAttributes != null) {
            result = ACCESS_DENIED;
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                for (ConfigAttribute var1 : dataBaseConfigAttributes) {
                    if (var1.getAttribute().equals(grantedAuthority.getAuthority())) {
                        result = ACCESS_GRANTED;
                    }
                }
            }
        }
        return result;
    }


    /**
     * 去权限配置中查找匹配的，权限配置
     *
     * @param path
     * @return
     */
    public Collection<ConfigAttribute> getConfigAttribute(String path) {
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : urlRequiredRoleMapping.entrySet()) {
            if (pathMatcher.match(entry.getKey(), path)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * bean 初始化设置属性完成后，加载数据库权限配置
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        reloadAuthority();
    }

    /**
     * 重数据库加载访问路径需要的角色
     */
    public void reloadAuthority(){
        urlRequiredRoleMapping.clear();
        List<Menu> menus = menuMapper.selectAllMenuAndRoles();
        menus.stream().filter(x -> !x.getParentMenu().equals(-1)).forEach(x -> {
            List<Role> roles = x.getRoles();
            if (!roles.isEmpty()) {
                String[] strings = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    strings[i] = "ROLE_" + roles.get(i).getRoleName();
                }
                urlRequiredRoleMapping.put(x.getHref() + "/**", SecurityConfig.createList(strings));
            }
        });
    }
}
