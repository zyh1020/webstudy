package com.zyh.webstudy.domain.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

// 角色
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRole implements GrantedAuthority, Serializable {
    private static final long serialVersionUID=1L;
    private Integer id;
    private String name;
    private String nameZh;
    @Override
    public String getAuthority() {
        return name;
    }
}
