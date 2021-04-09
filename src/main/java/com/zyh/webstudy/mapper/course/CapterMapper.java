package com.zyh.webstudy.mapper.course;

import com.zyh.webstudy.domain.course.Capter;

import java.util.List;

public interface CapterMapper {
    List<Capter> selectOneCourseAllCapter(Integer courseId);
    void insertOneCapter(Capter capter);
}
