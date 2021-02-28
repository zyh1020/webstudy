package com.zyh.webstudy.config.security.urlfilter;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/* 对访问url进行权限认证处理  */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    /*
    *  根据登录的用户（此时的用户中有用户的角色信息）
    *  判断 用户的角色信息 是否具有 访问url的角色
    * */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

        for (ConfigAttribute configAttribute : collection) {
            String needRole = configAttribute.getAttribute(); // 得到当前url需要的角色

            /* ①，默认的登录角色 */
            if("ROLE_LOGIN".equals(needRole)){
                // 判断是否登陆了
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("未登录!");
                }else {
                    return; // 已经登录但是在 根据url判断是否有访问权限访问UrlSecurityFilter中没有匹配到也表示不需要权限
                }
            }

            /* ②，不是默认的登陆角色，判断权限*/
            // 获取用户角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                // 只要包含其中一个角色即可访问
                if(authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }

        // 一种：用户未登录情况下访问受保护资源；二种：用户登录情况下访问被保护资源
        throw new AccessDeniedException("授权失败");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
