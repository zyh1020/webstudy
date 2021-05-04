package com.zyh.webstudy.service.community;

import com.zyh.webstudy.domain.community.Answer;

import java.util.List;
import java.util.Map;

public interface AnswerService {
    List<Answer> findAnswerByProblemId(Integer problemId);
    boolean addAnswer(Answer answer);
    Integer countSelectPersonOfAnswers(Map<String, Object> mapParams);
    List<Answer> selectPersonOfAnswers(Map<String, Object> mapParams);
    void updateAnswer(Answer answer);
    void deleteAnswer(Integer answerId);
}
