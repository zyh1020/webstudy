package com.zyh.webstudy.service.security.impl;

import com.zyh.webstudy.domain.security.SysMenu;
import com.zyh.webstudy.domain.security.SysRelation;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.mapper.security.SysUserMapper;
import com.zyh.webstudy.service.security.SysUserService;
import com.zyh.webstudy.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper  sysUserMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public SysUser findUserByUserName(String userName) {
        return sysUserMapper.selectUserByUserName(userName);
    }

    @Override
    public String userLogin(String userName, String passWord) throws AuthenticationException{
        Authentication authenticate = null;
        // 开始认证
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(userName,passWord);
        // 认证成功返回的就是userDetailsService查询数据库中的用户
        authenticate = authenticationManager.authenticate(upToken);
        SysUser sysUser = (SysUser)authenticate.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jwtTokenUtil.getJwtToken(sysUser.getUsername());
    }

    // 获取用户列表
    @Override
    public List<SysUser> findAllUser() {
        return sysUserMapper.selectAllUser();
    }

    @Override
    public void distributionRoles(String userId, String[] rolesId) {
        // ①，封装插入的对象
        List<SysRelation> sysRelations = new ArrayList<>();
        for (String roleId : rolesId) {
            SysRelation sysRelation = new SysRelation();
            sysRelation.setFId(Integer.parseInt(userId));
            sysRelation.setEId(Integer.parseInt(roleId));
            sysRelations.add(sysRelation);
        }
        // ②，删除原来的角色信息
        sysUserMapper.clearUserOfRoles(Integer.parseInt(userId));
        if(sysRelations.size() > 0){
            // ③，批量插入新的
            sysUserMapper.insertUserOfRoles(sysRelations);
        }
    }


}
