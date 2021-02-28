package com.zyh.webstudy;

import com.zyh.webstudy.domain.security.SysMenu;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.service.security.SysMenuService;
import com.zyh.webstudy.service.security.SysUserService;
import com.zyh.webstudy.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WebstudyApplicationTests {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysMenuService sysMenuService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Test
    void test03() {
        System.out.println(jwtTokenUtil);
    }

    @Test
    void test02() {
        List<SysMenu> sysMenus = sysMenuService.selectMensWithRole();
        for (SysMenu sysMenu : sysMenus) {
            System.out.println(sysMenu);
        }

    }
    @Test
    void test01() {
        SysUser zyh = sysUserService.findUserByUserName("zyh");
        System.out.println(zyh);
    }

}
