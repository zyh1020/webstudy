package com.zyh.webstudy.vo.community;

import com.zyh.webstudy.domain.security.SysUser;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 问题Vo类
 * @author: zyh
 * @date: 2021年04月16日 22:56
 */
@Data
@ApiModel(description = "用户实体类")
@EqualsAndHashCode(callSuper = false)
public class ProblemVo {
    private Integer id;
    private Integer personId;
    private Integer sortId;
    private Integer lookNum;
    private String problemTitle;
    private String problemContent;
    private Boolean delete;
    private Date createTime;
    private Date updateTime;
    private String twoLevelSortName;
}
