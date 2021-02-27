package com.zyh.webstudy.security.service;

import com.zyh.webstudy.security.domain.SysUser;

public interface SysUserService {
    // 通过用户用户名查询用户
    public SysUser findUserByUserName(String userName);
}
