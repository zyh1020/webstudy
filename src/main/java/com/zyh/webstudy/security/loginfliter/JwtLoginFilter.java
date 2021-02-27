package com.zyh.webstudy.security.loginfliter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyh.webstudy.security.domain.SysUser;
import com.zyh.webstudy.security.utils.JwtTokenUtil;
import com.zyh.webstudy.security.vo.UserLoginVo;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
/*
* 登录认证器
* */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;

    // 构造
    public JwtLoginFilter(AuthenticationManager authenticationManager,JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/user/login","POST"));
    }
    // 认证
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // 封装参数
        try {
            UserLoginVo userLoginVo = new ObjectMapper().readValue(request.getInputStream(), UserLoginVo.class);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userLoginVo.getUsername(), userLoginVo.getPassword());
            return authenticationManager.authenticate(authRequest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    // 认证成功
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
    {

        // 通过authResult.getPrincipal()方法可以得到，UserDetailsService方法返回的对象
        SysUser sysUser = (SysUser)authResult.getPrincipal();
        String jwtToken = jwtTokenUtil.getJwtToken(sysUser.getUsername());
        // 生成token
        try {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            Map resultMap = new HashMap();
            resultMap.put("code", "200");
            resultMap.put("msg", "认证通过！");
            resultMap.put("Authorization",jwtToken);
            out.write(new ObjectMapper().writeValueAsString(resultMap));
            out.flush();
            out.close();
        }catch (Exception outEx){
            outEx.printStackTrace();
        }
    }


    // 认证失败
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
    {
        if (failed instanceof UsernameNotFoundException || failed instanceof BadCredentialsException) {
            System.out.println("用户名不存在");
        }else if (failed instanceof LockedException) {
            System.out.println("账户被锁定，请联系管理员!");
        } else if (failed instanceof CredentialsExpiredException) {
            System.out.println("证书过期，请联系管理员!");
        } else if (failed instanceof AccountExpiredException) {
            System.out.println("账户过期，请联系管理员!");
        } else if (failed instanceof DisabledException) {
            System.out.println("账户被禁用，请联系管理员!");
        } else {
            System.out.println("登录失败!");
            try {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                PrintWriter out = response.getWriter();
                Map resultMap = new HashMap();
                resultMap.put("code", HttpServletResponse.SC_UNAUTHORIZED);
                resultMap.put("msg", "用户名或密码错误！");
                out.write(new ObjectMapper().writeValueAsString(resultMap));
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
