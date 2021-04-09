package com.zyh.webstudy.service.course.impl;

import com.zyh.webstudy.domain.course.Capter;
import com.zyh.webstudy.mapper.course.CapterMapper;
import com.zyh.webstudy.service.course.CapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 章节
 * @author: zyh
 * @date: 2021年04月09日 17:48
 */
@Service
public class CapterServiceImpl implements CapterService {

    @Autowired
    private CapterMapper capterMapper;

    /**
      *@Description: 查询某个课程下的所有章节
      *@Param: [courseId]
      *@return: java.util.List<com.zyh.webstudy.domain.course.Capter> 
      *@Author: zyh
      *@Date: 2021/4/9 18:18
     **/
    @Override
    public List<Capter> findCapter(Integer courseId) {
        return  capterMapper.selectOneCourseAllCapter(courseId);
    }

    /**
      *@Description: 添加章节信息
      *@Param: [capter]
      *@return: java.lang.String 
      *@Author: zyh
      *@Date: 2021/4/9 18:18
     **/
    @Override
    public Integer addCapter(Capter capter) {
        capterMapper.insertOneCapter(capter);
        return capter.getId();
    }


}
