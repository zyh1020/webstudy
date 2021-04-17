package com.zyh.webstudy.mapper.community;

import com.zyh.webstudy.domain.community.Answer;

import java.util.List;

public interface AnswerMapper {
    List<Answer> selectAnswers(Integer problemId);
    void insertAnswer(Answer answer);
}
