package com.zyh.webstudy.controller.community;

import com.zyh.webstudy.domain.community.Problem;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.service.community.ProblemService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return ResultUtil.success("提出问题成功");
    }

    @ApiOperation("修改问题")
    @PostMapping("/updateOneProblem")
    public ResultUtil updateOneProblem(@RequestBody Problem problem){
        problemService.updateOneProblem(problem);
        return ResultUtil.success("修改问题成功");
    }

    @ApiOperation("删除问题")
    @GetMapping("/deleteOneProblem/{problemId}")
    public ResultUtil deleteOneProblem(@PathVariable Integer problemId){
        problemService.deleteOneProblem(problemId);
        return ResultUtil.success("删除问题成功");
    }

    @ApiOperation("分页查询问题")
    @PostMapping("/findPageProblem/{currentPage}/{size}")
    public ResultUtil findPageProblem(@PathVariable Integer currentPage,@PathVariable Integer size,
                                      @RequestBody(required = false) Problem problem){
        Map<String,Object> mapParams = new HashMap<>();
        mapParams.put("currentPage",(currentPage-1)*size);
        mapParams.put("size",size);
        mapParams.put("condition",problem);
        Integer count = problemService.countPageSelectProblems(mapParams);
        List<Problem> problems = null;
        if(count > 0){
            problems = problemService.pageSelectProblems(mapParams);
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("problems",problems);
        resultMap.put("total",count);
        return ResultUtil.success("分页查询问题列表",resultMap);

    }

    @ApiOperation("查询某个用户的问题")
    @GetMapping("/findPersonOfProblem/{currentPage}/{size}")
    public ResultUtil findPersonOfProblem(@PathVariable Integer currentPage,
                                          @PathVariable Integer size
                                      ){
        Map<String,Object> mapParams = new HashMap<>();
        mapParams.put("currentPage",(currentPage-1)*size);
        mapParams.put("size",size);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        mapParams.put("personId",sysUser.getId());
        Integer count = problemService.countSelectPersonOfProblems(mapParams);
        List<Problem> problems = null;
        if(count > 0){
            problems = problemService.selectPersonOfProblems(mapParams);
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("problems",problems);
        resultMap.put("total",count);
        return ResultUtil.success("分页查询问题列表",resultMap);

    }


}
