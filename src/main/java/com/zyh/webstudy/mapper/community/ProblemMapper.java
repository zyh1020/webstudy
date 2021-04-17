package com.zyh.webstudy.mapper.community;

import com.zyh.webstudy.domain.community.Problem;

import java.util.List;

public interface ProblemMapper {
    public List<Problem> selectProblems(Problem problem);
    public Problem selectProblemById(Integer problemId);
    void inselectProbelm(Problem problem);
}
