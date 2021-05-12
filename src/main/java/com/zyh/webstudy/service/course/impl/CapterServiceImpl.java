package com.zyh.webstudy.service.course.impl;

import com.zyh.webstudy.domain.course.Capter;
import com.zyh.webstudy.mapper.course.CapterMapper;
import com.zyh.webstudy.mapper.course.VedioMapper;
import com.zyh.webstudy.service.course.CapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    private VedioMapper vedioMapper;

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
        capter.setDelete(false);
        capter.setCreateTime(new Date());
        capter.setUpdateTime(new Date());
        capterMapper.insertOneCapter(capter);
        return capter.getId();
    }

    @Override
    public void deleteOneCapter(Integer capterId) {
        // 删除章节的小节
        vedioMapper.deleteCapterOfVedios(capterId);
        // 删除章节
        capterMapper.deleteOneCapter(capterId);
    }

    @Override
    public void updateOneCapter(Capter capter) {
        capter.setUpdateTime(new Date());
        capterMapper.updateOneCapter(capter);
    }


}
