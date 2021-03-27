package com.zyh.webstudy.controller.security;

import com.zyh.webstudy.domain.security.SysMenu;
import com.zyh.webstudy.domain.security.SysRole;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.mapper.security.SysMenuMapper;
import com.zyh.webstudy.service.security.SysRoleService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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



    @ApiOperation("获取所有角色不包含菜单")
    @GetMapping("/findAllRoles")
    public ResultUtil findAllRoles(){
        // 获取登录后的用户
        List<SysRole> sysRoles = sysRoleService.getAllRoles();
        return ResultUtil.success("获取角色列表成功",sysRoles);
    }
    @ApiOperation("为角色分配权限")
    @PostMapping("/doAssion")
    public ResultUtil doAssion(@RequestParam("roleId")String roleId, @RequestParam("menusId")String[] menusId){
        // 获取登录后的用户
       sysRoleService.saveRoleMenus(roleId,menusId);
        return ResultUtil.success("为角色分配权限成功");
    }

    @ApiOperation("删除角色的权限")
    @GetMapping("/deleteAssion")
    public ResultUtil deleteAssion(String roleId,String menuId){
        // 获取登录后的用户
        List<SysMenu> sysMenus = sysRoleService.delteRoleMenus(roleId, menuId);
        return ResultUtil.success("删除权限，获取最新权限",sysMenus);
    }



}
