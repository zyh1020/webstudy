package com.zyh.webstudy.controller.course;

import com.zyh.webstudy.domain.course.Course;
import com.zyh.webstudy.service.course.CourseService;
import com.zyh.webstudy.utils.ResultUtil;
import com.zyh.webstudy.vo.course.CourseVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 课程
 * @author: zyh
 * @date: 2021年04月09日 11:03
 */
@RestController
@RequestMapping("/cou/info")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation("添加课程")
    @PostMapping("/addCourse")
    public ResultUtil addCourse(@RequestBody CourseVo courseVo){
        if(courseVo == null){
            return ResultUtil.error("参数不合法");
        }
        int courseId = courseService.insertOneCourse(courseVo);
        if(courseId > 0){
            return ResultUtil.error("课程信息保存成功！",courseId);
        }else {
            return ResultUtil.error("课程信息保存失败");
        }
    }

    @ApiOperation("查询课程基本信息")
    @GetMapping("/findCourse/{courseId}")
    public ResultUtil findCourse(@PathVariable String courseId){
        CourseVo courseVo = courseService.selectOneCourse(Integer.parseInt(courseId));
        return ResultUtil.success("查询用户信息成功",courseVo);
    }


    @ApiOperation("分页查询课程")
    @PostMapping("/findPageCourse/{current}/{limit}/{orderBy}")
    public ResultUtil findPageCourse(@PathVariable Integer current,
                                      @PathVariable Integer limit,
                                      @PathVariable(required = false) Integer orderBy,
                                      @RequestBody(required = false) Course course){
        // 拼接参数
        Map<String,Object> mapParams = new HashMap<>();
        mapParams.put("currentPage",(current-1)*limit);
        mapParams.put("selectNum",limit);
        mapParams.put("condition",course);
        mapParams.put("orderBy",orderBy);

        Integer count = courseService.countCourse(mapParams);
        List<Course> courses = null;
        if(count > 0){
            courses = courseService.findCourseList(mapParams);
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("courses",courses);
        resultMap.put("total",count);
        return ResultUtil.success("查询课程列表成功",resultMap);

    }


    @ApiOperation("查询首页课程")
    @GetMapping("/findCourses/{type}/{limit}")
    public ResultUtil findCourses(@PathVariable String type,
                                  @PathVariable Integer limit){
        List<Course> courses = courseService.findCourses(type,limit);
        return ResultUtil.success(courses);
    }

    @ApiOperation("删除课程的基本信息")
    @GetMapping("/deleteCourse/{courseId}")
    public ResultUtil deleteCourse(@PathVariable Integer courseId){
        Boolean isSuccess = courseService.deleteCourse(courseId);
        if(isSuccess){// 删除成功
            return ResultUtil.success("删除课程的基本信息成功");
        }else {
            return ResultUtil.error("删除课程的基本信息失败");
        }
    }



    @ApiOperation("修改课程")
    @PostMapping("/updateCourse")
    public ResultUtil updateCourse(@RequestBody CourseVo courseVo){
        if(courseVo == null){
            return ResultUtil.error("参数不合法");
        }
        boolean isSuccess = courseService.updateOneCourse(courseVo);
        if(isSuccess){
            return ResultUtil.error("修改课程信息成功！");
        }else {
            return ResultUtil.error("修改课程信息失败");
        }
    }
}
