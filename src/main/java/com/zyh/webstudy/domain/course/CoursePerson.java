package com.zyh.webstudy.domain.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 课程用户关系
 * @author: zyh
 * @date: 2021年05月07日 15:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CoursePerson {
    private Integer id;
    private Integer personId;
    private Integer courseId;
    private Boolean delete;
    private Date createTime;
    private Date updateTime;
}
