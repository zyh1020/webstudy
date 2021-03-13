package com.zyh.webstudy.service.security.impl;

import com.zyh.webstudy.domain.security.SysMenu;
import com.zyh.webstudy.mapper.security.SysRoleMapper;
import com.zyh.webstudy.service.security.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    // 获取所有的角色列表
    @Override
    public List<SysMenu> findAllRoles() {
        return sysRoleMapper.selectAllRoles();
    }
}
