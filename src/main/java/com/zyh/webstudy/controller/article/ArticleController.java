package com.zyh.webstudy.controller.article;

import com.zyh.webstudy.domain.article.Article;
import com.zyh.webstudy.service.article.ArticleService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/AddAnswer")
    public ResultUtil AddAnswers(@RequestBody Article article){
        Boolean isSuccess = articleService.addOneArticle(article);
        if(isSuccess){ // 添加问题成功
            return ResultUtil.success("添加文章成功");
        }else {
            return ResultUtil.error("添加文章失败");
        }

    }

    @ApiOperation("分页查询文章")
    @PostMapping("/findPageActicle/{current}/{limit}")
    public ResultUtil AddAnswers(@PathVariable Integer current,@PathVariable Integer limit,
                                 @RequestBody(required = false) Article article){

        Map<String,Object> mapParams = new HashMap<>();
        mapParams.put("currentPage",current);
        mapParams.put("selectNum",limit-1);
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
}

