package com.zyh.webstudy.security;

import com.zyh.webstudy.security.handle.UnauthEntryPointHandler;
import com.zyh.webstudy.security.handle.UrlAccessDeniedHandler;
import com.zyh.webstudy.security.loginfliter.JwtLoginFilter;
import com.zyh.webstudy.security.loginfliter.JwtVerifyFilter;
import com.zyh.webstudy.security.urlfilter.UrlAccessDecisionManager;
import com.zyh.webstudy.security.urlfilter.UrlSecurityFilter;
import com.zyh.webstudy.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService; // 用户认证来源
    @Autowired
    private UrlSecurityFilter urlSecurityFilter; // 访问url需要那些权限
    @Autowired
    private UrlAccessDecisionManager urlAccessDecisionManager; // 根据urlSecurityFilter访问url需要那些权限，判断用夫是否有权限
    @Autowired
    private UnauthEntryPointHandler unauthEntryPointHandler; // 未认证处理
    @Autowired
    private UrlAccessDeniedHandler urlAccessDeniedHandler; // 权限不足处理
    @Autowired
    private JwtTokenUtil jwtTokenUtil; // jwt生成解析工具

    // 加密
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //指定认证对象的来源
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //SpringSecurity配置信息
    public void configure(HttpSecurity http) throws Exception {
        // 关闭csrf
        http.csrf().disable();
        // 关闭session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 未认证
        http.exceptionHandling().authenticationEntryPoint(unauthEntryPointHandler);
        // 权限不足
        http.exceptionHandling().accessDeniedHandler(urlAccessDeniedHandler);

        // 认证器
        http.addFilter(new JwtLoginFilter(super.authenticationManager(),jwtTokenUtil));
        // 认证校验器
        http.addFilter(new JwtVerifyFilter(super.authenticationManager(),jwtTokenUtil,userDetailsService));


        // 动态权限配置
        http.authorizeRequests()
                .anyRequest() // 所有请求
                .authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(urlSecurityFilter);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                }); // 需要认证，认证是指登录

    }

    // 不拦截的
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/hello");
    }
}
