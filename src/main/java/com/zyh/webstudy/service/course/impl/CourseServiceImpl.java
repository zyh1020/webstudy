package com.zyh.webstudy.service.course.impl;

import com.zyh.webstudy.domain.course.Course;
import com.zyh.webstudy.domain.course.CourseDetails;
import com.zyh.webstudy.mapper.course.CourseDetailsMapper;
import com.zyh.webstudy.mapper.course.CourseMapper;
import com.zyh.webstudy.service.course.CourseService;
import com.zyh.webstudy.vo.course.CourseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    /**
      *@Description:  插入课程信息
      *@Param: [courseVo]
      *@return: java.lang.Integer 
      *@Author: zyh
      *@Date: 2021/4/9 16:16
     **/
    @Override
    public Integer insertOneCourse(CourseVo courseVo) {
        courseVo.setPersonId(1); // 先写死
        courseVo.setCourseStatus(0); // 未发布
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
}
