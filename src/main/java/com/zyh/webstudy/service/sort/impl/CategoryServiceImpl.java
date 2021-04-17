package com.zyh.webstudy.service.sort.impl;

import com.zyh.webstudy.domain.sort.Category;
import com.zyh.webstudy.mapper.sort.CategoryMapper;
import com.zyh.webstudy.service.sort.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 分类
 * @author: zyh
 * @date: 2021年04月06日 18:35
 */
@Service
public class 
CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
      *@Description: 添加分类
      *@Param: [category]
      *@return: java.lang.Boolean 
      *@Author: zyh
      *@Date: 2021/4/7 14:47
     **/
    @Override
    public Boolean insertOneCategory(Category category) {
        category.setDelete(false);
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        categoryMapper.insertOneCategory(category);
        return true;
    }

    /**
      *@Description: 查询所有分类
      *@Param: 
      *@return:  
      *@Author: zyh
      *@Date: 2021/4/7 14:47
     **/
    @Override
    public List<Category> selectAllCategory() {
        // ①，查询所有的一级分类
        List<Category> categories = categoryMapper.selectAllOneLevelCategory();

        // ②，查询每一个的二级分类
        for (Category category : categories) {
            List<Category> childrenCategories = categoryMapper.selectTwoLevelCategoryByParentId(category.getId());
            category.setChildren(childrenCategories);
        }
        return categories;
    }

    /**
      *@Description: 删除分类
      *@Param: [category]
      *@return: java.lang.Integer 
      *@Author: zyh
      *@Date: 2021/4/7 14:50
     **/
    @Override
    public Integer deleteOneCategory(Category category) {
        // ①，判断
        if(category.getLevel() == 1){ // 判断是否存在子分类
            List<Category> categories = categoryMapper.selectTwoLevelCategoryByParentId(category.getId());
            if (categories.size() > 0){
                return 1;
            }
        }

        // ②，判断是否还在使用
        if(isUse(category)){
            return 2;
        }
        // ③，删除分类
        categoryMapper.deleteOneCategory(category);
        return 0;
    }

    private boolean isUse(Category category){
        return false;
    }

    /**
      *@Description: 修改分类
      *@Param: [category]
      *@return: boolean 
      *@Author: zyh
      *@Date: 2021/4/7 15:18
     **/
    @Override
    public boolean updateOneCategory(Category category) {
        categoryMapper.updateOneCategory(category);
        return true;
    }

    /**
      *@Description:  查询所有分类以列表的形式
      *@Param: []
      *@return: java.util.List<com.zyh.webstudy.domain.sort.Category> 
      *@Author: zyh
      *@Date: 2021/4/9 9:39
     **/
    @Override
    public List<Category> selectAllCategoryList() {
        return categoryMapper.selectAllCategoryList();
    }
}
