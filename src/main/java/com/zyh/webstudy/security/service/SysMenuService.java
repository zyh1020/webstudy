package com.zyh.webstudy.security.service;

import com.zyh.webstudy.security.domain.SysMenu;

import java.util.List;

public interface SysMenuService {
    // 访问菜单需要那些角色
    public List<SysMenu> selectMensWithRole();
}
