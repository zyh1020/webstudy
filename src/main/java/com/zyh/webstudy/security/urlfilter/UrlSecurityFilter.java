package com.zyh.webstudy.security.urlfilter;


import com.zyh.webstudy.security.domain.SysMenu;
import com.zyh.webstudy.security.domain.SysRole;
import com.zyh.webstudy.security.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import java.util.Collection;
import java.util.List;


/**
 * 权限控制：根据url判断 是否有访问权限访问
 * */
@Component
public class UrlSecurityFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
     private SysMenuService sysMenuService;

    // 路径匹配规则的
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取请求的url
        String requestUrl = ((FilterInvocation)object).getRequestUrl();

        // 第一种：直接放行的url,不需要登录，不会在经过UrlAccessDecisionManager了
        String[] noAccess = {"/noUrlAccessDecisionManager"};
        for (String access : noAccess) {
            if(antPathMatcher.match(access,requestUrl)){
                return null;
            }
        }

        // 第二种：数据库中的匹配，返回url需要的权限
        List<SysMenu> menus = sysMenuService.selectMensWithRole(); // 得到所有的url和访问需要的角色
        for (SysMenu menu : menus) {
          // 得到和请求匹配的url
          if(antPathMatcher.match(menu.getUrl(),requestUrl)){
              List<SysRole> roles = menu.getSysRoles();
              String[] strings = roles.stream().map(SysRole::getName).toArray(String[]::new);
              return SecurityConfig.createList(strings);
          }
        }
        // 第三种：没有和数据库中的匹配上 默认为登录即可访问的
        return SecurityConfig.createList("ROLE_LOGIN") ;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
