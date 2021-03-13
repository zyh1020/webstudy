package com.zyh.webstudy.mapper.security;

import com.zyh.webstudy.domain.security.SysMenu;

import java.util.List;

public interface SysMenuMapper {
    // 访问菜单需要那些角色
    public List<SysMenu> selectMensWithRole();
    // 根据用户id查询用户的菜单列表
    public List<SysMenu> selectMensByUserId(Integer userId);
    // 查询所有菜单权限
    public List<SysMenu> selectMensAll();
}
