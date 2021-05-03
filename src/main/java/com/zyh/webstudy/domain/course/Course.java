package com.zyh.webstudy.domain.course;

import com.zyh.webstudy.domain.security.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 课程
 * @author: zyh
 * @date: 2021年04月07日 17:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Course {
    private Integer id;
    private Integer personId;
    private Integer sortId;
    private Integer sortParentId;
    private String title;
    private Integer courseTime;
    private Integer studyPersonNum;
    private Integer lookPersonNum;
    private String courseCover;
    private Integer difficulty;
    private String courseStatus;
    private Boolean delete;
    private Date createTime;
    private Date updateTime;
    private SysUser sysUser;

    private String sortName;
    private String sortParentName;
}
