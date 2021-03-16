package com.zyh.webstudy.controller.security;

import com.zyh.webstudy.domain.security.SysMenu;
import com.zyh.webstudy.domain.security.SysRole;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.service.security.SysRoleService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/roles")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("获取所有角色包含菜单")
    @GetMapping("/getAllRoles")
    public ResultUtil getAllRoles(){
        // 获取登录后的用户
        List<SysRole> sysRoles = sysRoleService.findAllRoles();
        return ResultUtil.success("获取角色列表成功",sysRoles);
    }

    @ApiOperation("为角色分配权限")
    @GetMapping("/doAssion")
    public ResultUtil doAssion(String roleId,String[] menusId){
        // 获取登录后的用户
        sysRoleService.saveRoleMenus(roleId,menusId);
        return ResultUtil.success("为角色分配权限成功");
    }

    @ApiOperation("获取所有角色不包含菜单")
    @GetMapping("/findAllRoles")
    public ResultUtil findAllRoles(){
        // 获取登录后的用户
        List<SysRole> sysRoles = sysRoleService.getAllRoles();
        return ResultUtil.success("获取角色列表成功",sysRoles);
    }

}
