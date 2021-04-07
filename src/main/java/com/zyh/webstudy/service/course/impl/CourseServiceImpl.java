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

    @Override
    public Boolean insertOneCourse(CourseVo courseVo) {
        courseVo.setCreateTime(new Date());
        courseVo.setUpdateTime(new Date());
        courseVo.setDelete(false);

        // 创建插入对象
        Course course = new Course();
        CourseDetails courseDetails = new CourseDetails();

        // 为对象赋值
        BeanUtils.copyProperties(courseVo,course);
        BeanUtils.copyProperties(courseVo,courseDetails);
        courseMapper.insertOneCourse(course);

        // 课程表和课程简介表的id一致
        courseDetails.setId(course.getId());
        courseDetailsMapper.insertOneCourse(courseDetails);
        return true;
    }
}
