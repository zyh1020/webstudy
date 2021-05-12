package com.zyh.webstudy.controller.security;


import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.service.security.SysUserService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/users")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("查询所有用户")
    @GetMapping("/getAllUser/{userName}")
    public ResultUtil getAllUser(@PathVariable String userName){
        if("all".equals(userName)){
            userName = null;
        }
        List<SysUser> allUser = sysUserService.findAllUser(userName);
        return ResultUtil.success("获取用户列表成功",allUser);
    }

    @ApiOperation("为用户分配角色")
    @PostMapping("/distributionRoles")
    public ResultUtil distrRoles(@RequestParam(value = "userId")String userId, @RequestParam(value = "rolesId", required = false)String[] rolesId){
        sysUserService.distributionRoles(userId,rolesId);
        return ResultUtil.success("为用户分配角色成功");
    }


    @ApiOperation("更改用户状态")
    @GetMapping("/setUserState/{userId}/{state}")
    public ResultUtil setUserState(@PathVariable Integer userId,@PathVariable Boolean state){
        Map<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        params.put("state",state);
        sysUserService.setUserState(params);
        return ResultUtil.success("更改用户状态成功");
    }


    @ApiOperation("删除用户")
    @GetMapping("/deleteOneUser/{userId}")
    public ResultUtil deleteOneUser(@PathVariable Integer userId){
        sysUserService.deleteOneUserById(userId);
        return ResultUtil.success("删除用户成功");
    }



}
