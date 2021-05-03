package com.zyh.webstudy.mapper.community;

import com.zyh.webstudy.domain.community.Problem;

import java.util.List;
import java.util.Map;

public interface ProblemMapper {
    Problem selectProblemById(Integer problemId);
    void inselectProbelm(Problem problem);
    Integer countPageFindProblemList(Map<String, Object> mapParams);
    List<Problem> pageFindProblemList(Map<String, Object> mapParams);
    Integer countSelectPersonOfProblems(Map<String, Object> mapParams);
    List<Problem> selectPersonOfProblems(Map<String, Object> mapParams);
    void updateOneProblem(Problem problem);

    void deleteOneProblem(Integer problemId);
}
