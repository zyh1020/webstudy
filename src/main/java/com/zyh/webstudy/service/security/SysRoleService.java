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

    // ④，删除角色的某个权限并返回最新角色权限
    List<SysMenu> delteRoleMenus(String roleId, String menusId);

    // 添加角色
    void addOneRoles(SysRole sysRole);
    // 修改角色
    void updateOneRoles(SysRole sysRole);
    // 删除角色
    void deleteOneRole(Integer roleId);
}
