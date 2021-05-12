package com.zyh.webstudy.service.course.impl;

import com.zyh.webstudy.domain.course.Course;
import com.zyh.webstudy.domain.course.CourseDetails;
import com.zyh.webstudy.domain.course.CoursePerson;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.mapper.course.CapterMapper;
import com.zyh.webstudy.mapper.course.CourseDetailsMapper;
import com.zyh.webstudy.mapper.course.CourseMapper;
import com.zyh.webstudy.mapper.course.VedioMapper;
import com.zyh.webstudy.service.course.CourseService;
import com.zyh.webstudy.vo.course.CourseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 课程
 * @author: zyh
 * @date: 2021年04月07日 17:56
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseDetailsMapper courseDetailsMapper;
    @Autowired
    private CapterMapper capterMapper;
    @Autowired
    private VedioMapper vedioMapper;

    /**
      *@Description:  插入课程信息
      *@Param: [courseVo]
      *@return: java.lang.Integer 
      *@Author: zyh
      *@Date: 2021/4/9 16:16
     **/
    @Override
    public Integer insertOneCourse(CourseVo courseVo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        courseVo.setPersonId(sysUser.getId());
        courseVo.setCourseStatus(false); // 未发布
        courseVo.setLookPersonNum(0); // 无人观看
        courseVo.setStudyPersonNum(0); // 无人学习
        courseVo.setCreateTime(new Date());
        courseVo.setUpdateTime(new Date());
        courseVo.setDelete(false);
        // 创建插入对象
        Course course = new Course();
        CourseDetails courseDetails = new CourseDetails();
        // 为对象赋值
        BeanUtils.copyProperties(courseVo,course);
        BeanUtils.copyProperties(courseVo,courseDetails);
        // ①，插入课程解基本信息
        courseMapper.insertOneCourse(course);
        // ②，插入课程简介信息
        courseDetails.setId(course.getId());// 课程表和课程简介表的id一致
        courseDetailsMapper.insertOneCourse(courseDetails);
        return course.getId();
    }

    /**
      *@Description: 查询课程信息
      *@Param: [courseId]
      *@return: com.zyh.webstudy.vo.course.CourseVo 
      *@Author: zyh
      *@Date: 2021/4/9 16:17
     **/
    @Override
    public CourseVo selectOneCourse(Integer courseId) {
        Course course = courseMapper.selectOneCourseInfo(courseId);
        CourseDetails courseDetails = courseDetailsMapper.selectOneCourseDetails(courseId);
        CourseVo courseVo = new CourseVo();
        BeanUtils.copyProperties(course,courseVo);
        BeanUtils.copyProperties(courseDetails,courseVo);
        return courseVo;
    }

    @Override
    public Integer countCourse(Map<String, Object> mapParams) {
        // 统计课程数目
        return courseMapper.countCoursePageNum(mapParams);
    }

    @Override
    public List<Course> findCourseList(Map<String, Object> mapParams) {
        // 查询课程列表
        return courseMapper.findCoursePageList(mapParams);
    }
    @Override
    public Boolean deleteOneCourse(Integer courseId) {
        try {
            // ①，删除课程基本信息和详细信息
            courseMapper.deleteOneCourse(courseId);
            courseDetailsMapper.deleteOneCourseDetail(courseId);
            // ②，删除章节信息和小节信息
            capterMapper.deleteCourseOfCapters(courseId);
            vedioMapper.deleteCourseOfVedios(courseId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public boolean updateOneCourse(CourseVo courseVo) {
        try {
            courseVo.setUpdateTime(new Date());
            // 创建插入对象
            Course course = new Course();
            CourseDetails courseDetails = new CourseDetails();
            // 为对象赋值
            BeanUtils.copyProperties(courseVo,course);
            BeanUtils.copyProperties(courseVo,courseDetails);
            courseMapper.updateOneCourse(course); // 修改基本信息
            courseDetailsMapper.upDateOneCourseDetail(courseDetails); // 修改基本课程介绍
        } catch (BeansException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public List<Course> findCourses(String type, Integer limit) {
        Map<String,Object> params = new HashMap<>();
        params.put("type",type);
        params.put("sizeLimit",limit);
        return courseMapper.findCourses(params);
    }

    @Override
    public void updateCourseStatus(Integer courseId, Boolean status) {
        Map<String,Object> params = new HashMap<>();
        params.put("courseId",courseId);
        params.put("status",status);
        courseMapper.updateCourseStatus(params);
    }

    @Override
    public void followCourse(CoursePerson coursePerson) {
        courseMapper.followCourse(coursePerson); // 关注课程
    }

    @Override
    public void cancelFollowCourses(Integer courseId, Integer personId) {
        Map<String,Object> params = new HashMap<>();
        params.put("courseId",courseId);
        params.put("personId",personId);
        courseMapper.cancelFollowCourses(params);
    }


    // 关注查询
    @Override
    public Integer countFollowCourseNum(Map<String, Object> mapParams) {
        return courseMapper.countFollowCourseNum(mapParams);
    }

    @Override
    public List<Course> followCourseList(Map<String, Object> mapParams) {
        return courseMapper.followCourseList(mapParams);
    }

    @Override
    public Integer isFollowCourses(Integer courseId, Integer personId) {
        Map<String,Object> params = new HashMap<>();
        params.put("courseId",courseId);
        params.put("personId",personId);
        return courseMapper.isFollowCourses(params);
    }


}
