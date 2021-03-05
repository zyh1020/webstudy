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
    void test04() {
        String zyh = jwtTokenUtil.getUserName("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6eWgiLCJjcmVhdGVkIjoxNjE0NTA1MzQxOTc3LCJleHAiOjE2MTQ1OTE3NDF9.m8jQnl3MJRlqfI0sF9-FZC7YAO3e3j5VSAENLwZJvhAM5AgSRFc4AzjU_AquvxXN-zKXh_cmYTcxWWkCM9H4wg");
        boolean zyh1 = jwtTokenUtil.validateToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6eWgiLCJjcmVhdGVkIjoxNjE0NTA1MzQxOTc3LCJleHAiOjE2MTQ1OTE3NDF9.m8jQnl3MJRlqfI0sF9-FZC7YAO3e3j5VSAENLwZJvhAM5AgSRFc4AzjU_AquvxXN-zKXh_cmYTcxWWkCM9H4wg", "zyh");

        System.out.println(zyh);
        System.out.println(zyh1);
    }


    @Test
    void test03() {
       String zyh = jwtTokenUtil.getJwtToken("zyh");
       System.out.println(zyh);
    }

    @Test
    void test02() {
        List<SysMenu> sysMenus = sysMenuService.findMensByUserId(1);
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
