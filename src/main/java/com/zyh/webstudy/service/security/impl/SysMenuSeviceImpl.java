package com.zyh.webstudy.service.security.impl;

import com.zyh.webstudy.domain.security.SysMenu;
import com.zyh.webstudy.mapper.security.SysMenuMapper;
import com.zyh.webstudy.service.security.SysMenuService;
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
        List<SysMenu> resultSysMenus = buildTreeMenus(sysMenus);
        return resultSysMenus;
    }

    /* 将菜单封装成树型结构 */
    private List<SysMenu> buildTreeMenus(List<SysMenu> sysMenus) {
        // 最终封装的结果集
        List<SysMenu> findMeuns = new ArrayList<>();

        // ①，获得入口，即得到层级为1的父节点，
        for(SysMenu currentSysMenu: sysMenus){
            if(0 == currentSysMenu.getParentId()){
                currentSysMenu.setLevel(1);
                // ②，调用查询子菜单
                // ③，存储在最终的结果集中
                findMeuns.add(selectChildren(currentSysMenu,sysMenus));
            }
        }
        return findMeuns;
    }

    /* 递归查询子节点 */
    private SysMenu selectChildren(SysMenu currentSysMenu, List<SysMenu> sysMenus) {

        // childrens用于存储sysMenu的子节点
        List<SysMenu> childrens = new ArrayList<>();

        // ①，遍历所有的sysMenus
        for(SysMenu ischildren : sysMenus){
            if(currentSysMenu.getId() == ischildren.getParentId()){ // 判断是否是当前节点的子节点
                Integer level = currentSysMenu.getLevel() + 1;
                ischildren.setLevel(level);
                // ②，递归调用
                childrens.add(selectChildren(ischildren,sysMenus));
            }
        }
        currentSysMenu.setChildren(childrens);
        return currentSysMenu;
    }
}
