package com.zyh.webstudy.controller.community;

import com.zyh.webstudy.domain.community.Answer;
import com.zyh.webstudy.domain.community.Problem;
import com.zyh.webstudy.service.community.AnswerService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("查询答案列表")
    @GetMapping("/findAnswers/{problemId}")
    public ResultUtil findAnswers(@PathVariable String problemId){
        List<Answer> Answers = answerService.findAnswerByProblemId(Integer.parseInt(problemId));
        return ResultUtil.success("查询答案成功",Answers);
    }

    @ApiOperation("添加答案")
    @PostMapping("/AddAnswer")
    public ResultUtil AddAnswers(@RequestBody Answer answer){
        boolean isSuccess= answerService.addAnswer(answer);
        return ResultUtil.success("添加问题成功");
    }
}
