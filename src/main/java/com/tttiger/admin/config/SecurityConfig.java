package com.tttiger.admin.config;

import com.tttiger.admin.security.DatabaseUserDetailServiceImpl;
import com.tttiger.admin.security.JsonUsernamePasswordAuthenticationFilter;
import com.tttiger.admin.security.RoleBasedVoter;
import com.tttiger.admin.security.VerifyCodeFilter;
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
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

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

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private LogoutHandler logoutHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private VerifyCodeFilter VerifyCodeFilter;

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
        // 在用户认证前添加验证码拦截器
        http
                .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/user/login")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler(failureHandler)
                .successHandler(successHandler)
                .and()

                .authorizeRequests().antMatchers("/captcha", "/pub-key","/security-confirm","/js/**", "/css/**",
                "/layui/**", "/lib/**", "/images/**","/favicon.ico").permitAll()
                .and()

                .logout().permitAll().logoutUrl("/logout").addLogoutHandler(logoutHandler)
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .accessDecisionManager(accessDecisionManager())
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and().addFilterAt(CustomAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
//                .and().addFilterBefore(VerifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public JsonUsernamePasswordAuthenticationFilter CustomAuthenticationFilter() throws Exception {
        JsonUsernamePasswordAuthenticationFilter filter = new JsonUsernamePasswordAuthenticationFilter();
        // 自定义认证过滤器需要重新指定登录成功处理器
        filter.setFilterProcessesUrl("/user/login");
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
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
