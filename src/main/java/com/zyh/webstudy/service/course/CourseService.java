package com.zyh.webstudy.service.course;

import com.zyh.webstudy.vo.course.CourseVo;

/**
 * @Description: 课程
 * @author: zyh
 * @date: 2021年04月07日 17:52
 */
public interface CourseService {
    Integer insertOneCourse(CourseVo courseVo);
    CourseVo selectOneCourse(Integer courseId);
}
