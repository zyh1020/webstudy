package com.zyh.webstudy.security.domain;

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
    private Boolean enabled;
    /* 角色：表示访问这样的url需要哪些角色 */
    private List<SysRole> sysRoles;

}
