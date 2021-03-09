package com.zyh.webstudy.controller.security;

import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.service.security.SysUserService;
import com.zyh.webstudy.utils.ResponseJsonUtil;
import com.zyh.webstudy.utils.ResultUtil;
import com.zyh.webstudy.vo.security.UserLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/*
*
* 用于登录
*
* */

@RestController
public class JwtLoginController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("登录返回token")
    @PostMapping("/user/login")
    public ResultUtil login(@RequestBody UserLoginVo userLoginVo){
        try {
            String token = sysUserService.userLogin(userLoginVo.getUsername(), userLoginVo.getPassword());
            return ResultUtil.success("登录成功",token);
        } catch (AuthenticationException e) {
            if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                return ResultUtil.error("用户名或密码错误");
            }else if (e instanceof LockedException) {
                return ResultUtil.error("账户被锁定，请联系管理员!");
            } else if (e instanceof CredentialsExpiredException) {
                return ResultUtil.error("证书过期，请联系管理员!");
            } else if (e instanceof AccountExpiredException) {
                return ResultUtil.error("账户过期，请联系管理员!");
            } else if (e instanceof DisabledException) {
                return ResultUtil.error("账户被禁用，请联系管理员");
            } else {
                return ResultUtil.error("用户名或密码错误");
            }
        }

    }

    @ApiOperation("退出登录")
    @GetMapping("/user/logout")
    public ResultUtil logout(){
        return ResultUtil.success("退出成功");
    }

    @ApiOperation("获取登录的用户信息")
    @GetMapping("/user/info")
    public ResultUtil info(Principal principal){
        if(null == principal){
            return ResultUtil.error("获取用户信息失败");
        }else{
            SysUser sysUser = sysUserService.findUserByUserName(principal.getName());
            sysUser.setPassword("");
            return ResultUtil.success("获取用户信息成功",sysUser);
        }
    }



}
