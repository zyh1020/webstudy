package com.zyh.webstudy.controller.security;

import com.zyh.webstudy.domain.security.SysMenu;
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

    @ApiOperation("查询所有的角色")
    @GetMapping("/getAllRoles")
    public ResultUtil getAllRoles(){
        // 获取登录后的用户
        List<SysMenu> allAuthority = sysRoleService.findAllRoles();
        return ResultUtil.success("获取角色列表成功",allAuthority);
    }
}
