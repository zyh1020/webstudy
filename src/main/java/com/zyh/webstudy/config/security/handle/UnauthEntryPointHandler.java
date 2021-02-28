package com.zyh.webstudy.config.security.handle;


import com.zyh.webstudy.utils.ResponseJsonUtil;
import com.zyh.webstudy.utils.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未认证 处理
* */
@Component
public class UnauthEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseJsonUtil.out(response, ResultUtil.error("用户未登录！"));
    }
}
