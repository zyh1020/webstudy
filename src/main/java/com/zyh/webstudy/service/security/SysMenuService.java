package com.zyh.webstudy.service.security;

import com.zyh.webstudy.domain.security.SysMenu;

import java.util.List;

public interface SysMenuService {
    // 访问菜单需要那些角色
    public List<SysMenu> selectMensWithRole();
}
