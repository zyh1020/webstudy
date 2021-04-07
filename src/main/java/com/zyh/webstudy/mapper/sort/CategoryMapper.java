package com.zyh.webstudy.mapper.sort;


import com.zyh.webstudy.domain.sort.Category;

import java.util.List;

public interface CategoryMapper {
    public void insertOneCategory(Category category);
    public List<Category> selectAllOneLevelCategory();
    public List<Category> selectTwoLevelCategoryByParentId(Integer parentId);
    public void deleteOneCategory(Category category);
    public void updateOneCategory(Category category);
}
