package com.zyh.webstudy.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
*  测试security的
*
* */
@RestController
public class TestController {

    /* 不经过securit拦截器链的 */
    @GetMapping("/hello")
    public String hello(){
        return "不经过securit拦截器链的";
    }

    /*
    * 在UrlSecurityFilter配置的，不用登陆认证，不需要权限的
    * 不经过访问url进行权限认证处理UrlAccessDecisionManager
    * */
    @GetMapping("/noUrlAccessDecisionManager")
    public String noUrlAccessDecisionManager(){
        return "不经过 访问url进行权限认证处理UrlAccessDecisionManager";
    }

    /* 登录才能访问 */
    @GetMapping("/user")
    public String user(){
        return "登录才能访问";
    }


    /* 用户zyh03的角色有系统管理员 ，可以访问 */
    @GetMapping("/system/cfg/kkk")
    public String api(){
        return "用户zyh03的角色有系统管理员 ，可以访问/system/cfg/**";
    }


    /* 用户zyh的角色有员工主管，可以访问*/
    @GetMapping("/employee/basic/kkk")
    public String admin(){
        return "用户zyh的角色有员工主管，可以访问/employee/basic/**";
    }
}
