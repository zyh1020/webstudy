package com.zyh.webstudy.mapper.security;

import com.zyh.webstudy.domain.security.SysMenu;

import java.util.List;

public interface SysRoleMapper {
    // 查询所有角色
    List<SysMenu> selectAllRoles();
}
