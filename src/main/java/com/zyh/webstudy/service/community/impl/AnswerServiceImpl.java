package com.zyh.webstudy.service.community.impl;

import com.zyh.webstudy.domain.community.Answer;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.mapper.community.AnswerMapper;
import com.zyh.webstudy.service.community.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 答案
 * @author: zyh
 * @date: 2021年04月16日 22:46
 */
@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerMapper answerMapper;
    /**
      *@Description: 查询问题的答案
      *@Param: [problemId]
      *@return: java.util.List<com.zyh.webstudy.domain.community.Answer> 
      *@Author: zyh
      *@Date: 2021/4/16 22:47
     **/
    @Override
    public List<Answer> findAnswerByProblemId(Integer problemId) {
        return answerMapper.selectAnswers(problemId);
    }

    @Override
    public boolean addAnswer(Answer answer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        answer.setPersonId(sysUser.getId());
        answer.setCreateTime(new Date());
        answer.setUpdateTime(new Date());
        answer.setDelete(false);
        answerMapper.insertAnswer(answer);
        return true;
    }
}
