package com.zyh.webstudy.service.community;

import com.zyh.webstudy.domain.community.Problem;

import java.util.List;

public interface ProblemService {
    public List<Problem> findProblems(Problem problem);
    public Problem findProblemById(Integer problemId);
    void addOneProblem(Problem problem);
}
