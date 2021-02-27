package com.zyh.webstudy.security.loginfliter;


import com.zyh.webstudy.security.utils.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
* 登录认证拦截器
* */

public class JwtVerifyFilter extends BasicAuthenticationFilter {

    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsService userDetailsService;

    public JwtVerifyFilter(AuthenticationManager authenticationManager,JwtTokenUtil jwtTokenUtil,UserDetailsService userDetailsService){
        super(authenticationManager);
        this.jwtTokenUtil =jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }


    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)throws IOException, ServletException {
        //获取当前认证成功用户权限信息
        //从header获取token
        String token = request.getHeader("token");
        if(!StringUtils.isEmpty(token)){
            // 获取用户信息
            String userName = jwtTokenUtil.getUserName(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            if(null != userName && null == SecurityContextHolder.getContext().getAuthentication()){
                UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authResult);
            }
        }
        chain.doFilter(request,response);
    }



}
