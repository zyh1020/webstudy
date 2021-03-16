package com.zyh.webstudy.service.security.impl;

import com.zyh.webstudy.domain.security.SysMenu;
import com.zyh.webstudy.domain.security.SysRelation;
import com.zyh.webstudy.domain.security.SysRole;
import com.zyh.webstudy.mapper.security.SysMenuMapper;
import com.zyh.webstudy.mapper.security.SysRoleMapper;
import com.zyh.webstudy.service.security.SysRoleService;
import com.zyh.webstudy.utils.TreeMenusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    // ①，获取所有的角色列表
    @Override
    public List<SysRole> findAllRoles() {
        // ①，查询所有的角色
        List<SysRole> sysRoles = sysRoleMapper.selectAllRoles();
        // ②，查询角色拥有的权限
        for(SysRole sysRole : sysRoles){
            List<SysMenu> sysMenus = sysMenuMapper.selectMenusByRolesId(sysRole.getId());
            // ③，将结果构建成树型结构
            List<SysMenu> resultMenus = TreeMenusUtil.buildTreeMenus(sysMenus);
            sysRole.setSysMenus(resultMenus);
        }
        return sysRoles;
    }

    // ②，为角色分配权限
    @Override
    public void saveRoleMenus(String roleId, String[] menusIds) {
        // sysMenus存储插入的值
        List<SysRelation> sysRelations = new ArrayList<>();
        for (String menusId : menusIds) {
            SysRelation sysRelation = new SysRelation();
            sysRelation.setFId(Integer.parseInt(menusId));
            sysRelation.setEId(Integer.parseInt(roleId));
            sysRelations.add(sysRelation);
        }
        // 清空角色roleId的权限
        sysRoleMapper.deleteRoleofMenus(Integer.parseInt(roleId));
        // 批量插入
        sysRoleMapper.insertMenus(sysRelations);

    }

    // ③，简单的获取所有的角色
    @Override
    public List<SysRole> getAllRoles() {
        return sysRoleMapper.selectAllRoles();
    }

}
