package com.zyh.webstudy.mapper.security;

import com.zyh.webstudy.domain.security.SysMenu;

import java.util.List;

public interface SysMenuMapper {
    // 访问菜单需要那些角色
    public List<SysMenu> selectMensWithRole();
}
