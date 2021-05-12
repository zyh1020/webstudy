package com.zyh.webstudy.mapper.course;

import com.zyh.webstudy.domain.course.Course;
import com.zyh.webstudy.domain.course.CoursePerson;
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
    void updateOneCourse(Course course);
    List<Course> findCourses(Map<String, Object> mapParams);
    void updateCourseStatus(Map<String, Object> params);
    void followCourse(CoursePerson coursePerson);
    void cancelFollowCourses(Map<String, Object> params);

    Integer countFollowCourseNum(Map<String, Object> mapParams);
    List<Course> followCourseList(Map<String, Object> mapParams);
    Integer isFollowCourses(Map<String, Object> params);
}
