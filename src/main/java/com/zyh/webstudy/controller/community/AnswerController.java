package com.zyh.webstudy.controller.community;

import com.zyh.webstudy.domain.community.Answer;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.service.community.AnswerService;
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
 * @Description: 答案
 * @author: zyh
 * @date: 2021年04月16日 22:48
 */
@RestController
@RequestMapping("/com/ans")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @ApiOperation("查询问题的答案列表")
    @GetMapping("/findAnswers/{problemId}")
    public ResultUtil findAnswers(@PathVariable String problemId){
        List<Answer> Answers = answerService.findAnswerByProblemId(Integer.parseInt(problemId));
        return ResultUtil.success("查询答案成功",Answers);
    }

    @ApiOperation("删除答案")
    @GetMapping("/deleteAnswer/{answerId}")
    public ResultUtil deleteAnswer(@PathVariable Integer answerId){
        answerService.deleteAnswer(answerId);
        return ResultUtil.success("删除答案成功");
    }

    @ApiOperation("修改答案")
    @PostMapping("/updateAnswer")
    public ResultUtil updateAnswer(@RequestBody Answer answer){
        answerService.updateAnswer(answer);
        return ResultUtil.success("修改答案");
    }


    @ApiOperation("添加答案")
    @PostMapping("/AddAnswer")
    public ResultUtil AddAnswers(@RequestBody Answer answer){
        boolean isSuccess= answerService.addAnswer(answer);
        return ResultUtil.success("添加问题成功");
    }


    @ApiOperation("查询某个用户的回答")
    @GetMapping("/findPersonOfAnswers/{currentPage}/{size}")
    public ResultUtil findPersonOfAnswers(@PathVariable Integer currentPage,
                                          @PathVariable Integer size
    ){
        Map<String,Object> mapParams = new HashMap<>();
        mapParams.put("currentPage",(currentPage-1)*size);
        mapParams.put("size",size);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        mapParams.put("personId",sysUser.getId());
        Integer count = answerService.countSelectPersonOfAnswers(mapParams);
        List<Answer> answers = null;
        if(count > 0){
            answers = answerService.selectPersonOfAnswers(mapParams);
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("answers",answers);
        resultMap.put("total",count);
        return ResultUtil.success("分页查询答案列表",resultMap);
    }



}
