package com.zyh.webstudy.security.handle;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 未认证
* */
@Component
public class UnauthEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Map resultMap = new HashMap();
        resultMap.put("code", "200");
        resultMap.put("msg", "未登录！");
        out.write(new ObjectMapper().writeValueAsString(resultMap));
        out.flush();
        out.close();
    }
}
