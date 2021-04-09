package com.zyh.webstudy.service.course.impl;

import com.zyh.webstudy.domain.course.Vedio;
import com.zyh.webstudy.mapper.course.VedioMapper;
import com.zyh.webstudy.service.course.VedioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 小节或视频
 * @author: zyh
 * @date: 2021年04月09日 18:28
 */
@Service
public class VedioServiceImpl implements VedioService {
    @Autowired
    private VedioMapper vedioMapper;
    /**
      *@Description: 添加小节或视频
      *@Param: [vedio]
      *@return: java.lang.Integer 
      *@Author: zyh
      *@Date: 2021/4/9 18:31
     **/
    @Override
    public Integer addVedio(Vedio vedio) {
        vedioMapper.insertOneVedio(vedio);
        return vedio.getId();
    }
}
