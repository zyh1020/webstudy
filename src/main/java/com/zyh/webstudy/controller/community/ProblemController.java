package com.zyh.webstudy.controller.community;

import com.zyh.webstudy.domain.community.Problem;
import com.zyh.webstudy.service.community.ProblemService;
import com.zyh.webstudy.utils.ResultUtil;
import com.zyh.webstudy.vo.community.ProblemVo;
import com.zyh.webstudy.vo.course.CourseVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 问题
 * @author: zyh
 * @date: 2021年04月16日 21:37
 */
@RestController
@RequestMapping("/com/pro")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @ApiOperation("查询问题列表")
    @PostMapping("/findProblems")
    public ResultUtil findProblems(@RequestBody ProblemVo problemVo){
        Problem problem = new Problem();
        BeanUtils.copyProperties(problemVo,problem);
        List<Problem> problems = problemService.findProblems(problem);
        return ResultUtil.success("查询问题列表成功",problems);
    }

    @ApiOperation("查询问题详情")
    @GetMapping("/findOneProblem/{problemId}")
    public ResultUtil findOneProblem(@PathVariable String problemId){
        Problem problem = problemService.findProblemById(Integer.parseInt(problemId));
        return ResultUtil.success("查询问题详情成功",problem);
    }

    @ApiOperation("提出问题")
    @PostMapping("/addOneProblem")
    public ResultUtil addOneProblem(@RequestBody Problem problem){
        problemService.addOneProblem(problem);
        return ResultUtil.success("提出问题成功",problem);
    }

}
