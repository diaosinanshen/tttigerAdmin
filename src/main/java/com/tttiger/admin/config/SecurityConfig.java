package com.tttiger.admin.config;

import com.tttiger.admin.security.CustomAccessDeniedHandler;
import com.tttiger.admin.security.DatabaseUserDetailServiceImpl;
import com.tttiger.admin.security.RoleBasedVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Arrays;
import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/11/04 17:36
 * @Description
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DatabaseUserDetailServiceImpl userDetailService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //推荐使用该密码加密类
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //是否隐藏没有找到用户的异常
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        //设置userService实例
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        //设置密码加密类
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("开始配置权限...............4");
        //过滤静态登录页面和静态资源,这里的/路径都是指的是controller请求路径并不是指静态页面路径
        http
                .formLogin()
                .loginPage("/login").permitAll()
                .and()

                .authorizeRequests().antMatchers("/login", "/js/**", "/css/**",
                "/layui/**", "/lib/**", "/images/**", "/favicon.ico").permitAll()
                .and()

                .formLogin().loginProcessingUrl("/user/login")
                .usernameParameter("managerAccount").passwordParameter("managerPassword")
                .failureForwardUrl("/loginFail").defaultSuccessUrl("/loginCheck")

                .and()
                .logout().permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .accessDecisionManager(accessDecisionManager())
                .and().exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()).accessDeniedPage("/denied");
    }

    @Autowired
    private RoleBasedVoter roleBasedVoter;

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                new WebExpressionVoter(),
                roleBasedVoter,
                new AuthenticatedVoter());
        return new UnanimousBased(decisionVoters);
    }

}
