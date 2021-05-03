package com.zyh.webstudy.service.community;

import com.zyh.webstudy.domain.community.Problem;

import java.util.List;
import java.util.Map;

public interface ProblemService {
    Problem findProblemById(Integer problemId);
    void addOneProblem(Problem problem);
    Integer countPageSelectProblems(Map<String, Object> mapParams);
    List<Problem> pageSelectProblems(Map<String, Object> mapParams);
    Integer countSelectPersonOfProblems(Map<String, Object> mapParams);
    List<Problem> selectPersonOfProblems(Map<String, Object> mapParams);
    void updateOneProblem(Problem problem);
    void deleteOneProblem(Integer problemId);
}
