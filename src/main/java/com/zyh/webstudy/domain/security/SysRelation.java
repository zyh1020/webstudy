package com.zyh.webstudy.domain.security;

import lombok.Data;
import lombok.EqualsAndHashCode;

// 关系表 ：角色和菜单 或则 用户和菜单
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRelation {

    private Integer id;
    private Integer fId;
    private Integer eId;
}
