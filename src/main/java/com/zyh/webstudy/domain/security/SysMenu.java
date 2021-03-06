package com.zyh.webstudy.domain.security;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

// 菜单
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenu implements Serializable {
    private static final long serialVersionUID=1L;
    private Integer id;
    private Integer parentId;
    private String url;
    private String path;
    private String component;
    private String name;
    private String icon;
    private Boolean hidden;
    private Boolean enabled;
    /* 菜单层级 */
    private Integer level;
    /* 角色：表示访问这样的url需要哪些角色 */
    private List<SysRole> sysRoles;
    /* 子菜单 */
    private List<SysMenu> children;

}
