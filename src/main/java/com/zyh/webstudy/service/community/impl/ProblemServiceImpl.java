package com.zyh.webstudy.service.community.impl;

import com.zyh.webstudy.domain.community.Problem;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.mapper.community.ProblemMapper;
import com.zyh.webstudy.service.community.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 问题
 * @author: zyh
 * @date: 2021年04月16日 21:32
 */
@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    /**
      *@Description:  查询问题列表
      *@Param: [problem]
      *@return: java.util.List<com.zyh.webstudy.domain.community.Problem> 
      *@Author: zyh
      *@Date: 2021/4/16 21:34
     **/
    @Override
    public List<Problem> findProblems(Problem problem) {
        return problemMapper.selectProblems(problem);
    }
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
}
