package com.zyh.webstudy.service.security.impl;

import com.zyh.webstudy.domain.security.SysMenu;
import com.zyh.webstudy.mapper.security.SysMenuMapper;
import com.zyh.webstudy.service.security.SysMenuService;
import com.zyh.webstudy.utils.TreeMenusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuSeviceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Override
    public List<SysMenu> selectMensWithRole() {
        return sysMenuMapper.selectMensWithRole();
    }
    @Override
    public List<SysMenu> findMensByUserId(Integer userId) {
        return sysMenuMapper.selectMensByUserId(userId);
    }

    /* 查询所有菜单 */
    @Override
    public List<SysMenu> findAllAuthority() {
        // ①，查询所有的菜单
        List<SysMenu> sysMenus = sysMenuMapper.selectMensAll();
        // ②，将菜单封装成树型结构
        List<SysMenu> resultSysMenus = TreeMenusUtil.buildTreeMenus(sysMenus);
        return resultSysMenus;
    }

    /* 删除菜单 */
    @Override
    public void removeMenu(Integer mId) {

        // ①，deleteIds用于存储需要删除的id
        List<Integer> deleteIds = new ArrayList<>();
        deleteIds.add(mId);
        // ②，删除的id存储在deleteIds中
        selectDeleteIds(mId,deleteIds);
        sysMenuMapper.deleteMenusByIds(deleteIds);
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
