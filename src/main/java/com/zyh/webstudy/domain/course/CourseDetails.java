package com.zyh.webstudy.domain.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 课程详情
 * @author: zyh
 * @date: 2021年04月07日 17:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseDetails {
    private Integer id;
    private String description;
    private Boolean delete;
    private Date createTime;
    private Date updateTime;
}
