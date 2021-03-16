package com.zyh.webstudy.domain.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

// 角色
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRole implements GrantedAuthority, Serializable {
    private static final long serialVersionUID=1L;
    private Integer id;
    private String name;
    private String nameZh;
    /* 拥有的菜单 */
    private List<SysMenu> sysMenus;
    @Override
    public String getAuthority() {
        return name;
    }
}
