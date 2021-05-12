package com.zyh.webstudy.service.course;

import com.zyh.webstudy.domain.course.Capter;

import java.util.List;

public interface CapterService {
    List<Capter> findCapter(Integer courseId);
    Integer addCapter(Capter capter);
    // 删除章节
    void deleteOneCapter(Integer capterId);
    // 修改章节
    void updateOneCapter(Capter capter);
}
