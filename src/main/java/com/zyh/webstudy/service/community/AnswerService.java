package com.zyh.webstudy.service.community;

import com.zyh.webstudy.domain.community.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> findAnswerByProblemId(Integer problemId);
    boolean addAnswer(Answer answer);
}
