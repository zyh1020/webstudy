package com.zyh.webstudy.mapper.community;

import com.zyh.webstudy.domain.community.Answer;
import com.zyh.webstudy.domain.community.Problem;

import java.util.List;
import java.util.Map;

public interface AnswerMapper {
    List<Answer> selectAnswers(Integer problemId);
    void insertAnswer(Answer answer);
    void deleteProblemOfAnswers(Integer problemId);
    Integer countSelectPersonOfAnswers(Map<String, Object> mapParams);
    List<Answer> selectPersonOfAnswers(Map<String, Object> mapParams);
    void updateAnswer(Answer answer);
    void deleteAnswer(Integer answerId);
}
