package com.zyh.webstudy.service.sort;

import com.zyh.webstudy.domain.sort.Category;

import java.util.List;

public interface CategoryService {
    Boolean insertOneCategory(Category category);
    List<Category> selectAllCategory();
    Integer deleteOneCategory(Category category);
    boolean updateOneCategory(Category category);
    List<Category> selectAllCategoryList();
}
