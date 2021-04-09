package com.zyh.webstudy.controller.sort;

import com.zyh.webstudy.domain.sort.Category;
import com.zyh.webstudy.service.sort.CategoryService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 分类
 * @author: zyh
 * @date: 2021年04月07日 9:42
 */
@RestController
@RequestMapping("/sort/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @ApiOperation("查询所有分类")
    @GetMapping("/findAll")
    public ResultUtil findAll(){
        List<Category> categories = categoryService.selectAllCategory();
        return ResultUtil.success("查询所有分类成功",categories);
    }

    @ApiOperation("查询所有分类-列表")
    @GetMapping("/findAllList")
    public ResultUtil findAllList(){
        List<Category> categories = categoryService.selectAllCategoryList();
        return ResultUtil.success("查询所有分类成功",categories);
    }


    @ApiOperation("添加分类")
    @PostMapping("/addOneCategory")
    public ResultUtil addOneCategory(@RequestBody Category category){
        boolean addSuccess = categoryService.insertOneCategory(category);
        if(addSuccess){
            return ResultUtil.success("添加分类成功",true);
        }else {
            return ResultUtil.error("添加分类失败",false);
        }
    }

    @ApiOperation("删除分类")
    @PostMapping("/deleteOneCategory")
    public ResultUtil deleteOneCategory(@RequestBody Category category){
        int deleteSuccess = categoryService.deleteOneCategory(category);
        if(deleteSuccess == 0){
            return ResultUtil.success("删除分类成功",true);
        }else if(deleteSuccess == 1){
            return ResultUtil.error("删除分类失败,该分类还有子分类",false);
        }else if(deleteSuccess == 2){
            return ResultUtil.error("删除分类失败,该分类还在使用",false);
        }else {
            return ResultUtil.error("删除分类失败",false);
        }
    }


    @ApiOperation("修改分类")
    @PostMapping("/updateCategory")
    public ResultUtil updateOneCategory(@RequestBody Category category){
        boolean updateSuccess = categoryService.updateOneCategory(category);
        if(updateSuccess){
            return ResultUtil.success("修改分类成功",true);
        }else {
            return ResultUtil.error("修改分类失败",false);
        }
    }

}
