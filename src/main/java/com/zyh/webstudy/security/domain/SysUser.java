package com.zyh.webstudy.security.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

// 用户
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements UserDetails, Serializable {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String userAvatar; // 头像
    private String remark;
    private Boolean enabled;

    /* 角色: 表示该用户拥有那些角色 */
    private List<SysRole> sysRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return sysRoles;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
