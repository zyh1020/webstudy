package com.zyh.webstudy.domain.community;

import com.zyh.webstudy.domain.security.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 问题
 * @author: zyh
 * @date: 2021年04月16日 20:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Problem {
    private Integer id;
    private Integer personId;
    private Integer sortId;
    private Integer lookNum;
    private String problemTitle;
    private String problemContent;
    private Boolean delete;
    private Date createTime;
    private Date updateTime;

    private SysUser sysUser;
    private String twoLevelSortName;
}
