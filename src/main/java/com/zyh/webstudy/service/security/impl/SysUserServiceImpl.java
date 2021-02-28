package com.zyh.webstudy.service.security.impl;

import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.mapper.security.SysUserMapper;
import com.zyh.webstudy.service.security.SysUserService;
import com.zyh.webstudy.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper  sysUserMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public SysUser findUserByUserName(String userName) {
        return sysUserMapper.selectUserByUserName(userName);
    }

    @Override
    public String userLogin(String userName, String passWord) throws AuthenticationException{
        Authentication authenticate = null;
        // 开始认证
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(userName,passWord);
        // 认证成功返回的就是userDetailsService查询数据库中的用户
        authenticate = authenticationManager.authenticate(upToken);
        SysUser sysUser = (SysUser)authenticate.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jwtTokenUtil.getJwtToken(sysUser.getUsername());
    }
}
