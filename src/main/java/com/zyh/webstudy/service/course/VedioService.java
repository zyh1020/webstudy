package com.zyh.webstudy.service.course;

import com.zyh.webstudy.domain.course.Vedio;

public interface VedioService {
    Integer addVedio(Vedio vedio);
    void deleteOneVedio(Integer vedioId);
    void updateVedio(Vedio vedio);
}
