package com.zyh.webstudy.mapper.security;

import com.zyh.webstudy.domain.security.SysRelation;
import com.zyh.webstudy.domain.security.SysRole;

import java.util.List;

public interface SysRoleMapper {
    // 查询所有角色
    List<SysRole> selectAllRoles();

    // 为角色分配权限
    void insertMenus(List<SysRelation> sysRelations);

    // 删除角色的权限
    void deleteRoleofMenus(Integer roleId);
}
