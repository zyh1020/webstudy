package com.zyh.webstudy.mapper.course;

import com.zyh.webstudy.domain.course.Course;
import com.zyh.webstudy.vo.course.CourseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CourseMapper {
    int insertOneCourse(Course course);
    Course selectOneCourseInfo(Integer courseId);
    Integer countCoursePageNum(Map<String, Object> mapParams);
    List<Course> findCoursePageList(Map<String, Object> mapParams);

    void deleteOneCourse(Integer courseId);

    void updateOneCourse(CourseVo courseVo);

    List<Course> findCourses(Map<String, Object> mapParams);
}
