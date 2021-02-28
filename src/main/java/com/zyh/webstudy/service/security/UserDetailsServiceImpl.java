package com.zyh.webstudy.service.security;

import com.zyh.webstudy.domain.security.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/*
*
*  secrity 权限控制的 查询组件
*
* */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findUserByUserName(username);
        if(null == sysUser){
            // 用户名不存在
            throw new UsernameNotFoundException("用户不存在");
        }else{
            return sysUser;
        }

    }
}
