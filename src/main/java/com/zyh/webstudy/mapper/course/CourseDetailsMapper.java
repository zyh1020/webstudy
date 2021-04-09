package com.zyh.webstudy.mapper.course;


import com.zyh.webstudy.domain.course.CourseDetails;

public interface CourseDetailsMapper {
    int insertOneCourse(CourseDetails courseDetails);
    CourseDetails selectOneCourseDetails(Integer courseId);
}
