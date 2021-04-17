package com.zyh.webstudy.controller.course;

import com.zyh.webstudy.service.course.CourseService;
import com.zyh.webstudy.utils.ResultUtil;
import com.zyh.webstudy.vo.course.CourseVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("查询课程")
    @GetMapping("/findCourse/{courseId}")
    public ResultUtil findCourse(@PathVariable String courseId){
        CourseVo courseVo = courseService.selectOneCourse(Integer.parseInt(courseId));
        return ResultUtil.success("查询用户信息成功",courseVo);
    }

}
