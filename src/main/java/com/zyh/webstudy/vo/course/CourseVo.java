package com.zyh.webstudy.vo.course;

import com.zyh.webstudy.domain.security.SysUser;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 课程vo类
 * @author: zyh
 * @date: 2021年04月07日 19:47
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "课程vo类")
public class CourseVo {
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
    private Boolean courseStatus;
    private Boolean delete;
    private Date createTime;
    private Date updateTime;
    private String description;
    private SysUser sysUser;
}
