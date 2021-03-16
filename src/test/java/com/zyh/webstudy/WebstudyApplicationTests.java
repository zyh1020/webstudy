package com.zyh.webstudy;

import com.zyh.webstudy.domain.security.SysMenu;
import com.zyh.webstudy.domain.security.SysRelation;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.mapper.security.SysMenuMapper;
import com.zyh.webstudy.mapper.security.SysRoleMapper;
import com.zyh.webstudy.service.security.SysMenuService;
import com.zyh.webstudy.service.security.SysUserService;
import com.zyh.webstudy.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class WebstudyApplicationTests {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysMenuService sysMenuService;


    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @Test
    void test09() {
        String uId = "100";
        String[] roles = {"100","101","102"};
        sysUserService.distributionRoles(uId,roles);
    }

    @Test
    void test08() {
        sysRoleMapper.deleteRoleofMenus(30);
    }
    @Test
    void test07() {
        List<SysRelation> sysRelations = new ArrayList<>();
        for(int i= 100;i<130;i++){
            SysRelation sysRelation = new SysRelation();
            sysRelation.setFId(i);
            sysRelation.setEId(30);
            sysRelations.add(sysRelation);
        }
        sysRoleMapper.insertMenus(sysRelations);
    }

    @Test
    void test06() {
        sysMenuService.removeMenu(100);
    }
    @Test
    void test05() {
        List<SysMenu> sysMenus = sysMenuMapper.selectMenuOfChildrens(1);
        for (SysMenu sysMenu : sysMenus) {
            System.out.println(sysMenu);
        }
    }

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
