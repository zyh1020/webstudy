package com.zyh.webstudy.controller.security;

import com.zyh.webstudy.domain.security.SysMenu;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.service.security.SysUserService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/users")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("查询所有用户")
    @GetMapping("/getAllUser")
    public ResultUtil getAllUser(){
        List<SysUser> allUser = sysUserService.findAllUser();
        return ResultUtil.success("获取用户列表成功",allUser);
    }

    @ApiOperation("为用户分配角色")
    @PostMapping("/distributionRoles")
    public ResultUtil distrRoles(@RequestParam(value = "userId")String userId, @RequestParam(value = "rolesId", required = false)String[] rolesId){
        sysUserService.distributionRoles(userId,rolesId);
        return ResultUtil.success("为用户分配角色成功");
    }


}
