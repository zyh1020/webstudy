package com.zyh.webstudy.service.security.impl;

import com.zyh.webstudy.domain.security.SysMenu;
import com.zyh.webstudy.mapper.security.SysMenuMapper;
import com.zyh.webstudy.service.security.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
}
