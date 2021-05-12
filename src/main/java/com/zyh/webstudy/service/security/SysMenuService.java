package com.zyh.webstudy.service.security;

import com.zyh.webstudy.domain.security.SysMenu;
import io.swagger.models.auth.In;

import java.util.List;

public interface SysMenuService {
    // 访问菜单需要那些角色
    List<SysMenu> selectMensWithRole();
    //
    List<SysMenu> findMensByUserId(Integer userId);
    // 查询所有菜单
    List<SysMenu> findAllAuthority();
    // 删除菜单
    void removeMenu(Integer mId);

    // 修改菜单
    void updateOneMenu(SysMenu sysMenu);

    void addOneMenu(SysMenu sysMenu);
}
