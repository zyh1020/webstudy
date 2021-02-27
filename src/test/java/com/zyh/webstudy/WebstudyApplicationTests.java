package com.zyh.webstudy;

import com.zyh.webstudy.security.domain.SysMenu;
import com.zyh.webstudy.security.domain.SysUser;
import com.zyh.webstudy.security.service.SysMenuService;
import com.zyh.webstudy.security.service.SysUserService;
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
