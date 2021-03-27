package com.zyh.webstudy.mapper.security;

import com.zyh.webstudy.domain.security.SysRelation;
import com.zyh.webstudy.domain.security.SysRole;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper {
    // 查询所有角色
    List<SysRole> selectAllRoles();

    // 为角色分配权限
    void insertMenus(List<SysRelation> sysRelations);

    // 删除角色的全部权限
    void deleteRoleofMenus(Integer roleId);

    // 删除角色拥有某些的权限
    void deleteRoleMenus(Map<String, Object> params);
}
