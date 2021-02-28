package com.zyh.webstudy.config.security.loginfliter;


import com.zyh.webstudy.utils.JwtTokenUtil;
import com.zyh.webstudy.utils.ResponseJsonUtil;
import com.zyh.webstudy.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
* 登录认证拦截器
* */

@Component
public class JwtVerifyFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)throws IOException, ServletException {
        //获取当前认证成功用户权限信息
        //从header获取token
        String token = request.getHeader(jwtTokenUtil.getTokenHead());
        if(!StringUtils.isEmpty(token)){
            // 校验并获取用户信息
            String userName = jwtTokenUtil.getUserName(token);
            if(null != userName && null == SecurityContextHolder.getContext().getAuthentication()){
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                // 判断jwt是否有效
                if(jwtTokenUtil.validateToken(token,userDetails.getUsername())){
                    UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authResult);
                }
            }
        }
        chain.doFilter(request,response);
    }


}
