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
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.session.*;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import java.util.ArrayList;
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

    @Autowired
    private SessionRegistry sessionRegistry;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //推荐使用该密码加密类
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 解决不能注入session注册表问题
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * 解决session失效后 sessionRegistry中session没有同步失效的问题，启用并发session控制，首先需要在配置中增加下面监听器
     *
     * @return
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
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
        JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter = UsernamePasswordAuthenticationFilter();
        // 在用户认证前添加验证码拦截器
        http
                .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/user/login")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler(failureHandler)
                .successHandler(successHandler)
                .and()

                .authorizeRequests().antMatchers("/captcha", "/pub-key", "/security-confirm", "/js/**", "/css/**",
                "/layui/**", "/lib/**", "/images/**", "/favicon.ico").permitAll()
                .and()

                .logout().permitAll().logoutUrl("/logout").addLogoutHandler(logoutHandler)
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .accessDecisionManager(accessDecisionManager())
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and().addFilterAt(jsonUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//              .addFilterBefore(VerifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加 Session管理器
        http.sessionManagement()
                // Session失效后跳转到这个链接
                .invalidSessionUrl("/user/login")
                //最大session并发数量，超过定义数量前一个session就会失效
                .maximumSessions(1)
                //Session达到最大有效数的时候，不再允许相同的账户登录。
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry);
    }

    @Bean
    public JsonUsernamePasswordAuthenticationFilter UsernamePasswordAuthenticationFilter() throws Exception {
        JsonUsernamePasswordAuthenticationFilter filter = new JsonUsernamePasswordAuthenticationFilter();
        // 自定义认证过滤器需要重新指定登录成功处理器
        filter.setFilterProcessesUrl("/user/login");
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failureHandler);
        filter.setAuthenticationManager(authenticationManagerBean());
        /**
         * 自定义session管理策略，由于自定义了UsernamePasswordAuthenticationFilter,
         * HttpSecurity的sessionManagement() 方法无法完成自动配置，所以手动将这几个session策略配置进来
         */
        List<SessionAuthenticationStrategy> strategies = new ArrayList<>();
        // 注册session
        // 对request.changeSessionId() 方法做出相应
        strategies.add(new ChangeSessionIdAuthenticationStrategy());
        strategies.add(new RegisterSessionAuthenticationStrategy(sessionRegistry));
        // 控制同账号登陆数量
        strategies.add(new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry));
        CompositeSessionAuthenticationStrategy strategy = new CompositeSessionAuthenticationStrategy(strategies);
        filter.setSessionAuthenticationStrategy(strategy);
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
