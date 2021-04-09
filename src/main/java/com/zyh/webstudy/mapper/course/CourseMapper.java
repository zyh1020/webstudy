package com.zyh.webstudy.mapper.course;

import com.zyh.webstudy.domain.course.Course;

public interface CourseMapper {
    int insertOneCourse(Course course);
    Course selectOneCourseInfo(Integer courseId);
}
