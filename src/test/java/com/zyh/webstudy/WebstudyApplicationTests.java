package com.zyh.webstudy;

import com.zyh.webstudy.domain.sort.Category;
import com.zyh.webstudy.domain.security.SysMenu;
import com.zyh.webstudy.domain.security.SysRelation;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.mapper.security.SysMenuMapper;
import com.zyh.webstudy.mapper.security.SysRoleMapper;
import com.zyh.webstudy.service.course.CourseService;
import com.zyh.webstudy.service.sort.CategoryService;
import com.zyh.webstudy.service.security.SysMenuService;
import com.zyh.webstudy.service.security.SysUserService;
import com.zyh.webstudy.utils.JwtTokenUtil;
import com.zyh.webstudy.vo.course.CourseVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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

    @Autowired
    CategoryService categoryService;
    @Autowired
    CourseService courseService;

    @Test
    void test13() {
        CourseVo courseVo = new CourseVo();
        courseVo.setTitle("标题");
        courseVo.setDescription("简介");
        courseService.insertOneCourse(courseVo);
    }

    @Test
    void test12() {
        List<Category> categories = categoryService.selectAllCategory();
        for (Category category : categories) {
            System.out.println(category);
        }
    }

    @Test
    void test11() {
        Category category = new Category();
        category.setUpdateTime(new Date());
        category.setCreateTime(new Date());
        category.setParentId(0);
        category.setLevel(1);
        category.setDelete(false);
        category.setName("前端");
        categoryService.insertOneCategory(category);
    }

    @Test
    void test10() {

        List<Integer> deleteIds = new ArrayList<>();
        deleteIds.add(13);
        deleteIds.add(14);

        // ②，创建删除参数
        Map<String,Object> params = new HashMap<>();
        params.put("roleId",1);
        params.put("deleteIds",deleteIds);
        sysRoleMapper.deleteRoleMenus(params);
    }

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
