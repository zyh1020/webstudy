package com.zyh.webstudy.security.mapper;

import com.zyh.webstudy.security.domain.SysUser;

public interface SysUserMapper {
    // 通过用户用户名查询用户
    public SysUser selectUserByUserName(String userName);
}
