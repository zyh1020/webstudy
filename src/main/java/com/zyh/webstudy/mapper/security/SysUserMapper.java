package com.zyh.webstudy.mapper.security;

import com.zyh.webstudy.domain.security.SysUser;

public interface SysUserMapper {
    // 通过用户用户名查询用户
    public SysUser selectUserByUserName(String userName);
}
