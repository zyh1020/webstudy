package com.zyh.webstudy.mapper.security;

import com.zyh.webstudy.domain.security.SysRelation;
import com.zyh.webstudy.domain.security.SysUser;

import java.util.List;

public interface SysUserMapper {
    // 通过用户用户名查询用户
    SysUser selectUserByUserName(String userName);
    // 获取所有用户
    List<SysUser> selectAllUser();
    // 清空某个用户的角色信息
    void clearUserOfRoles(int userId);
    // 批量插入某个用户的角色-为用户分配角色
    void insertUserOfRoles(List<SysRelation> sysRelations);
}
