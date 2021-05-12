package com.zyh.webstudy.service.security;

import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.vo.user.UserUpdateVo;
import com.zyh.webstudy.vo.user.UserRegisterVo;
import org.springframework.security.core.AuthenticationException;

import java.util.List;
import java.util.Map;

public interface SysUserService {
    // 通过用户用户名查询用户
    SysUser findUserByUserName(String userName);
    // 用户登录
    String userLogin(String userName,String passWord) throws AuthenticationException;
    // 获取所有的用户
    List<SysUser> findAllUser(String userName);
    // 为用户分配角色
    void distributionRoles(String userId, String[] rolesId);
    void updateUserHeard(UserUpdateVo userUpdateVo);
    void updateUserInfo(UserUpdateVo userUpdateVo);
    void insertUser(UserRegisterVo userRegisterVo);
    SysUser findUserByPhone(String phone);
    void setUserState(Map<String,Object> params);
    void deleteOneUserById(Integer userId);
}
