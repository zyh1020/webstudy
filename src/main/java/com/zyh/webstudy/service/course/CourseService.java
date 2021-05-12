package com.zyh.webstudy.service.course;

import com.zyh.webstudy.domain.course.Course;
import com.zyh.webstudy.domain.course.CoursePerson;
import com.zyh.webstudy.vo.course.CourseVo;

import java.util.List;
import java.util.Map;

/**
 * @Description: 课程
 * @author: zyh
 * @date: 2021年04月07日 17:52
 */
public interface CourseService {
    // 插入课程信息
    Integer insertOneCourse(CourseVo courseVo);
    // 查询课程
    CourseVo selectOneCourse(Integer courseId);
    // 分页查询课程
    Integer countCourse(Map<String, Object> mapParams);
    List<Course> findCourseList(Map<String, Object> mapParams);
    // 删除课程
    Boolean deleteOneCourse(Integer courseId);
    // 更新课程
    boolean updateOneCourse(CourseVo courseVo);
    // 根据不同的排序方式查询课程
    List<Course> findCourses(String type, Integer limit);
    // 修改课程状态
    void updateCourseStatus(Integer courseId, Boolean status);

    // 关注课程
    void followCourse(CoursePerson coursePerson);

    // 取消关注
    void cancelFollowCourses(Integer courseId, Integer personId);

    // 关注查询
    Integer countFollowCourseNum(Map<String, Object> mapParams);
    List<Course> followCourseList(Map<String, Object> mapParams);
    // 判断是否关注过
    Integer isFollowCourses(Integer courseId, Integer personId);
}
