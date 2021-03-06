package com.zyh.webstudy.controller.user;

import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.service.security.SysUserService;
import com.zyh.webstudy.utils.ResultUtil;
import com.zyh.webstudy.vo.user.UserUpdateVo;
import com.zyh.webstudy.vo.user.UserLoginVo;
import com.zyh.webstudy.vo.user.UserRegisterVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @Description: 用户
 * @author: zyh
 * @date: 2021年05月01日 14:55
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("登录返回token")
    @PostMapping("/jwtLogin")
    public ResultUtil login(@RequestBody UserLoginVo userLoginVo, HttpServletRequest request){
        String code = (String)request.getSession().getAttribute(userLoginVo.getUsername());
        // 判断验证码是否正确
        if(code == null || !code.equals(userLoginVo.getCode())){
            return ResultUtil.error("验证码不正确");
        }
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
    @GetMapping("/jwtLogout")
    public ResultUtil logout(){
        return ResultUtil.success("退出成功");
    }

    @ApiOperation("获取登录的用户信息")
    @GetMapping("/jwtInfo")
    public ResultUtil info(Principal principal){
        if(null == principal){
            return ResultUtil.error("获取用户信息失败");
        }else{
            SysUser sysUser = sysUserService.findUserByUserName(principal.getName());
            sysUser.setPassword("");
            return ResultUtil.success("获取用户信息成功",sysUser);
        }
    }



    @ApiOperation("注册用户")
    @PostMapping("/insertUser")
    public ResultUtil insertUser(@RequestBody UserRegisterVo userRegisterVo,HttpServletRequest request){
        String code = (String)request.getSession().getAttribute(userRegisterVo.getPhone());
        // 判断验证码是否正确
        if(code == null || !code.equals(userRegisterVo.getCode())){
            return ResultUtil.error("验证码不正确");
        }
        SysUser sysUser = sysUserService.findUserByPhone(userRegisterVo.getPhone());
        if(sysUser != null){
            return ResultUtil.error("该号码已经注册了");
        }

        // 判断验证码是否正确
        sysUserService.insertUser(userRegisterVo);
        return ResultUtil.success("注册用户成功");
    }

    @ApiOperation("修改用户头像")
    @PostMapping("/updateUserHeard")
    public ResultUtil updateUserHeard(@RequestBody UserUpdateVo userUpdateVo){
        // 判断验证码是否正确
        sysUserService.updateUserHeard(userUpdateVo);
        return ResultUtil.success("修改用户头像成功");
    }

    @ApiOperation("修改用户基础信息")
    @PostMapping("/updateUserInfo")
        public ResultUtil updateUserInfo(@RequestBody UserUpdateVo userUpdateVo){
        // 判断验证码是否正确
        sysUserService.updateUserInfo(userUpdateVo);
        return ResultUtil.success("修改用户基础信息成功");
    }


}
