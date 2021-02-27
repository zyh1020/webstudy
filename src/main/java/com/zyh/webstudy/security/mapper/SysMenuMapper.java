package com.zyh.webstudy.security.mapper;

import com.zyh.webstudy.security.domain.SysMenu;

import java.util.List;

public interface SysMenuMapper {
    // 访问菜单需要那些角色
    public List<SysMenu> selectMensWithRole();
}
