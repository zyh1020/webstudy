package com.zyh.webstudy.config.security;

import com.zyh.webstudy.config.security.handle.UnauthEntryPointHandler;
import com.zyh.webstudy.config.security.handle.UrlAccessDeniedHandler;
import com.zyh.webstudy.config.security.loginfliter.JwtVerifyFilter;
import com.zyh.webstudy.config.security.urlfilter.UrlAccessDecisionManager;
import com.zyh.webstudy.config.security.urlfilter.UrlSecurityFilter;
import com.zyh.webstudy.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
* security核心配置
* */
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
    private JwtVerifyFilter jwtVerifyFilter; // jwt登录校验器

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
        // 认证校验器
        http.addFilterBefore(jwtVerifyFilter, UsernamePasswordAuthenticationFilter.class);
        // 未认证处理
        http.exceptionHandling().authenticationEntryPoint(unauthEntryPointHandler);

        //



        // 动态权限配置
        http.authorizeRequests()
                .anyRequest() // 所有请求
                .authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(urlSecurityFilter); // url拦截
                        o.setAccessDecisionManager(urlAccessDecisionManager); // 授权
                        return o;
                    }
                }); // 需要认证，认证是指登录

        // 权限不足处理
        http.exceptionHandling().accessDeniedHandler(urlAccessDeniedHandler);

    }

    // 认证器
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // 不拦截的
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers(
                    "/hello",
                    "/error",
                    "/user/login", // 登录
                    "/user/jwtLogin",// 登录
                    "/user/insertUser",// 注册
                    "/css/**",
                    "/js/**",
                    "/index.html",
                    "favicon.ico",
                    "/doc.html",
                    "/webjars/**",
                    "/swagger-resources/**",
                    "/v2/api-docs/**");
    }

}
