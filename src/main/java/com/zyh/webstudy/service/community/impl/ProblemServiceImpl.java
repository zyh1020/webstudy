package com.zyh.webstudy.service.community.impl;

import com.zyh.webstudy.domain.community.Problem;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.mapper.community.AnswerMapper;
import com.zyh.webstudy.mapper.community.ProblemMapper;
import com.zyh.webstudy.service.community.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 问题
 * @author: zyh
 * @date: 2021年04月16日 21:32
 */
@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;
    @Autowired
    private AnswerMapper answerMapper;

    /**
      *@Description: 查询问题详情
      *@Param: [problemId]
      *@return: com.zyh.webstudy.domain.community.Problem
      *@Author: zyh
      *@Date: 2021/4/16 21:50
     **/
    @Override
    public Problem findProblemById(Integer problemId) {
        return problemMapper.selectProblemById(problemId);
    }

    @Override
    public void addOneProblem(Problem problem) {
        problem.setCreateTime(new Date());
        problem.setUpdateTime(new Date());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        problem.setPersonId(sysUser.getId());
        problem.setDelete(false);
        problem.setLookNum(0);
        problemMapper.inselectProbelm(problem);
    }

    @Override
    public Integer countPageSelectProblems(Map<String, Object> mapParams) {
        return problemMapper.countPageFindProblemList(mapParams);
    }

    @Override
    public List<Problem> pageSelectProblems(Map<String, Object> mapParams) {
        return problemMapper.pageFindProblemList(mapParams);
    }

    @Override
    public Integer countSelectPersonOfProblems(Map<String, Object> mapParams) {
        return problemMapper.countSelectPersonOfProblems(mapParams);
    }

    @Override
    public List<Problem> selectPersonOfProblems(Map<String, Object> mapParams) {
        return problemMapper.selectPersonOfProblems(mapParams);
    }

    @Override
    public void updateOneProblem(Problem problem) {
        problemMapper.updateOneProblem(problem);
    }

    @Override
    public void deleteOneProblem(Integer problemId) {
        // 删除答案
        answerMapper.deleteProblemOfAnswers(problemId);
        // 删除问题
        problemMapper.deleteOneProblem(problemId);
    }
}
