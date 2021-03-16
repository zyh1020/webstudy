package com.zyh.webstudy.service.security;

import com.zyh.webstudy.domain.security.SysUser;
import org.springframework.security.core.AuthenticationException;

import java.util.List;

public interface SysUserService {
    // 通过用户用户名查询用户
    public SysUser findUserByUserName(String userName);

    // 用户登录
    public String userLogin(String userName,String passWord) throws AuthenticationException;

    // 获取所有的用户
    List<SysUser> findAllUser();

    // 为用户分配角色
    void distributionRoles(String userId, String[] rolesId);
}
