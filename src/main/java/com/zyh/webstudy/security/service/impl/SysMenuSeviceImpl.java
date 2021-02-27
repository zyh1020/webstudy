package com.zyh.webstudy.security.service.impl;

import com.zyh.webstudy.security.domain.SysMenu;
import com.zyh.webstudy.security.mapper.SysMenuMapper;
import com.zyh.webstudy.security.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
