package com.zyh.webstudy.service.security;

import com.zyh.webstudy.domain.security.SysMenu;
import com.zyh.webstudy.domain.security.SysRole;

import java.util.List;

public interface SysRoleService {
    // ①，获取所有的角色列表
    List<SysRole> findAllRoles();

    // ②，为角色分配权限
    void saveRoleMenus(String roleId, String[] menusId);

    // ③，简单的获取所有角色
    List<SysRole> getAllRoles();


}
