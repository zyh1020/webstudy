package com.zyh.webstudy.service.course;

import com.zyh.webstudy.domain.course.Capter;

import java.util.List;

public interface CapterService {
    List<Capter> findCapter(Integer courseId);
    Integer addCapter(Capter capter);
}
