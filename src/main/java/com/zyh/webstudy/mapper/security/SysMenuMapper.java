package com.zyh.webstudy.mapper.security;

import com.zyh.webstudy.domain.security.SysMenu;

import java.util.List;

public interface SysMenuMapper {
    // 访问菜单需要那些角色
    List<SysMenu> selectMensWithRole();
    // 根据用户id查询用户的菜单列表
    List<SysMenu> selectMensByUserId(Integer userId);
    // 查询所有菜单权限
    List<SysMenu> selectMensAll();

    // 查询mId的子菜单
    List<SysMenu> selectMenuOfChildrens(Integer mId);

    // 删除菜单
    void deleteMenusByIds(List<Integer> deleteIds);
    // 查询角色拥有的权限列表
    List<SysMenu> selectMenusByRolesId(Integer roleId);

    void updateOneMenu(SysMenu sysMenu);

    void addOneMenu(SysMenu sysMenu);
}
