package com.zyh.webstudy.mapper.security;

import com.zyh.webstudy.domain.security.SysRelation;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.vo.user.UserUpdateVo;
import com.zyh.webstudy.vo.user.UserRegisterVo;

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

    // 修改用户头像
    void updateUserHeard(UserUpdateVo userUpdateVo);
    // 修改基本信息
    void updateUserInfo(UserUpdateVo userUpdateVo);

    void insertUserInfo(UserRegisterVo userRegisterVo);

    SysUser findUserByPhone(String phone);
}
