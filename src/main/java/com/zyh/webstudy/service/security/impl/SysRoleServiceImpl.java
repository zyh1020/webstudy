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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // ④，删除角色的某个权限
    @Override
    public List<SysMenu> delteRoleMenus(String roleId, String menusId) {
        int mId = Integer.parseInt(menusId);
        // ①，获取menusId的所有的子
        List<Integer> deleteIds = new ArrayList<>();
        deleteIds.add(mId);
        selectDeleteIds(mId,deleteIds);

        // ②，创建删除参数
        Map<String,Object> params = new HashMap<>();
        params.put("roleId",Integer.parseInt(roleId));
        params.put("deleteIds",deleteIds);

        // ③，批量删除
        sysRoleMapper.deleteRoleMenus(params);

        // ④，查询最新权限
        List<SysMenu> sysMenus = sysMenuMapper.selectMenusByRolesId(Integer.parseInt(roleId));
        List<SysMenu> resultMenus = TreeMenusUtil.buildTreeMenus(sysMenus);
        return resultMenus;
    }

    @Override
    public void addOneRoles(SysRole sysRole) {
        sysRoleMapper.insertOneRoles(sysRole);
    }

    @Override
    public void updateOneRoles(SysRole sysRole) {
        sysRoleMapper.updateOneRoles(sysRole);
    }

    @Override
    public void deleteOneRole(Integer roleId) {
        sysRoleMapper.deleteOneRole(roleId);
    }

    /* 将需要删除的id存储在deleteIds中 */
    private void selectDeleteIds(Integer mId, List<Integer> deleteIds) {
        // ①，查询mId的子菜单
        List<SysMenu> childrens = sysMenuMapper.selectMenuOfChildrens(mId);
        // 遍历
        for(SysMenu sysMenu : childrens){
            Integer sysMenuId = sysMenu.getId();
            deleteIds.add(sysMenuId);
            // ②，递归
            selectDeleteIds(sysMenuId,deleteIds);
        }
    }

}
