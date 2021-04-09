package com.zyh.webstudy.controller.course;

import com.zyh.webstudy.domain.course.Capter;
import com.zyh.webstudy.service.course.CapterService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 章节
 * @author: zyh
 * @date: 2021年04月09日 17:49
 */
@RestController
@RequestMapping("/cou/capter")
public class CapterController {

    @Autowired
    private CapterService capterService;

    @ApiOperation("查询某个课程的所有章节")
    @GetMapping("/findCapters/{courseId}")
    public ResultUtil findCapter(@PathVariable String courseId){
        List<Capter> capters = capterService.findCapter(Integer.parseInt(courseId));
        return ResultUtil.success("查询章节成功！",capters);
    }

    @ApiOperation("添加章节")
    @PostMapping("/addCapter")
    public ResultUtil addCapter(@RequestBody Capter capter){
       Integer capterId = capterService.addCapter(capter);
        return ResultUtil.success("添加章节成功！",capterId);
    }

}
