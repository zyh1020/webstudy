package com.zyh.webstudy.mapper.course;

import com.zyh.webstudy.domain.course.Vedio;

public interface VedioMapper {
    void insertOneVedio(Vedio vedio);
    void deleteCourseOfVedios(Integer courseId);
    void deleteCapterOfVedios(Integer capterId);
    void deleteVedioById(Integer vedioId);
    void updateVedio(Vedio vedio);
}
