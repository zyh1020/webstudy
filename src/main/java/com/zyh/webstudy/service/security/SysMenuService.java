package com.zyh.webstudy.service.security;

import com.zyh.webstudy.domain.security.SysMenu;
import io.swagger.models.auth.In;

import java.util.List;

public interface SysMenuService {
    // 访问菜单需要那些角色
    public List<SysMenu> selectMensWithRole();
    //
    public List<SysMenu> findMensByUserId(Integer userId);
    // 查询所有菜单
    public List<SysMenu> findAllAuthority();
}
