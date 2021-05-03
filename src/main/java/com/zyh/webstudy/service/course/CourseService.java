package com.zyh.webstudy.service.course;

import com.zyh.webstudy.domain.course.Course;
import com.zyh.webstudy.vo.course.CourseVo;

import java.util.List;
import java.util.Map;

/**
 * @Description: 课程
 * @author: zyh
 * @date: 2021年04月07日 17:52
 */
public interface CourseService {
    Integer insertOneCourse(CourseVo courseVo);
    CourseVo selectOneCourse(Integer courseId);

    Integer countCourse(Map<String, Object> mapParams);

    List<Course> findCourseList(Map<String, Object> mapParams);

    Boolean deleteOneCourse(Integer courseId);

    boolean updateOneCourse(CourseVo courseVo);

    Boolean deleteCourse(Integer courseId);

    List<Course> findCourses(String type, Integer limit);
}
