package com.zyh.webstudy.security.service.impl;

import com.zyh.webstudy.security.domain.SysUser;
import com.zyh.webstudy.security.mapper.SysUserMapper;
import com.zyh.webstudy.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper  sysUserMapper;

    @Override
    public SysUser findUserByUserName(String userName) {
        return sysUserMapper.selectUserByUserName(userName);
    }
}
