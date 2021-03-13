package com.zyh.webstudy.service.security;

import com.zyh.webstudy.domain.security.SysMenu;

import java.util.List;

public interface SysRoleService {
    // ①，获取所有的角色列表
    List<SysMenu> findAllRoles();
}
