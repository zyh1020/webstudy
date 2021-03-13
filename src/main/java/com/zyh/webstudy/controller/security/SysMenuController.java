package com.zyh.webstudy.controller.security;

import com.zyh.webstudy.domain.security.SysMenu;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.service.security.SysMenuService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/system/menus")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;
    @ApiOperation("根据用户id查询菜单列表")
    @GetMapping("/getMenusByUserId")
    public ResultUtil getMenusByUserId(){
        // 获取登录后的用户
        SysUser sysUser = (SysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<SysMenu> menus = sysMenuService.findMensByUserId(sysUser.getId());
        return ResultUtil.success("菜单列表",menus);
    }


    @ApiOperation("查询所有的权限")
    @GetMapping("/getAllAuthority")
    public ResultUtil  getAllAuthority(){
        // 获取登录后的用户
        List<SysMenu> allAuthority = sysMenuService.findAllAuthority();
        return ResultUtil.success("获取权限列表成功",allAuthority);
    }
}
