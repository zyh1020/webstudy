package com.zyh.webstudy.controller.article;

import com.zyh.webstudy.domain.article.Article;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.service.article.ArticleService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 文章
 * @author: zyh
 * @date: 2021年04月17日 17:47
 */
@RestController
@RequestMapping("/art/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ApiOperation("添加文章")
    @PostMapping("/AddArticle")
    public ResultUtil AddArticle(@RequestBody Article article){
        Boolean isSuccess = articleService.addOneArticle(article);
        if(isSuccess){ // 添加问题成功
            return ResultUtil.success("添加文章成功");
        }else {
            return ResultUtil.error("添加文章失败");
        }

    }


    @ApiOperation("修改文章")
    @PostMapping("/updateArticle")
    public ResultUtil updateArticle(@RequestBody Article article){
        Boolean isSuccess = articleService.updateOneArticle(article);
        if(isSuccess){ // 添加问题成功
            return ResultUtil.success("修改文章成功");
        }else {
            return ResultUtil.error("修改文章失败");
        }

    }


    @ApiOperation("删除文章")
    @GetMapping("/deleteArticle/{articleId}")
    public ResultUtil deleteArticle(@PathVariable Integer articleId){
        Boolean isSuccess = articleService.deleteOneArticle(articleId);
        if(isSuccess){ // 添加问题成功
            return ResultUtil.success("删除文章成功");
        }else {
            return ResultUtil.error("删除文章失败");
        }

    }


    @ApiOperation("分页查询文章")
    @PostMapping("/findPageActicle/{current}/{limit}")
    public ResultUtil findPageActicle(@PathVariable Integer current,@PathVariable Integer limit,
                                 @RequestBody(required = false) Article article){

        Map<String,Object> mapParams = new HashMap<>();
        mapParams.put("currentPage",(current-1)*limit);
        mapParams.put("selectNum",limit);
        mapParams.put("condition",article);
        Integer count = articleService.countPageSelectArticles(mapParams);
        List<Article> articles = null;
        if(count > 0){
            articles = articleService.pageSelectArticles(mapParams);
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("articles",articles);
        resultMap.put("total",count);
       return ResultUtil.success("查询文章列表成功",resultMap);

    }


    @ApiOperation("查询文章")
    @GetMapping("/findOneActicle/{articleId}")
    public ResultUtil findOneActicle(@PathVariable Integer articleId){
        Article article = articleService.getOneActicleById(articleId);
        return ResultUtil.success("查询文章成功",article);
    }

    @ApiOperation("分页查询个人文章")
    @GetMapping("/findPersonOfActicle/{current}/{limit}")
    public ResultUtil findPersonOfActicle(@PathVariable Integer current,
                                          @PathVariable Integer limit){

        Map<String,Object> mapParams = new HashMap<>();
        mapParams.put("currentPage",(current-1)*limit);
        mapParams.put("selectNum",limit);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        mapParams.put("personId",sysUser.getId());
        Integer count = articleService.countPersonSelectArticles(mapParams);
        List<Article> articles = null;
        if(count > 0){
            articles = articleService.personSelectArticles(mapParams);
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("articles",articles);
        resultMap.put("total",count);
        return ResultUtil.success("查询文章列表成功",resultMap);
    }

}

