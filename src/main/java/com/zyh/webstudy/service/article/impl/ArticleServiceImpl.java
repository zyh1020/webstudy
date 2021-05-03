package com.zyh.webstudy.service.article.impl;

import com.zyh.webstudy.domain.article.Article;
import com.zyh.webstudy.domain.security.SysUser;
import com.zyh.webstudy.mapper.article.ArticleMapper;
import com.zyh.webstudy.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 文章
 * @author: zyh
 * @date: 2021年04月17日 17:41
 */
@Service
public class
ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    /**
     * 添加文章
     * @param article
     * @return
     */
    @Override
    public Boolean addOneArticle(Article article) {
        article.setLookNum(0);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article.setDelete(false);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        article.setPersonId(sysUser.getId());
        articleMapper.insertOneArticle(article);
        return true;
    }




    /**
      *@Description: 分页查询文章列表
      *@Param: [mapParams]
      *@return: java.util.List<com.zyh.webstudy.domain.article.Article>
      *@Author: zyh
      *@Date: 2021/4/17 20:46
     **/
    @Override
    public List<Article> pageSelectArticles(Map<String, Object> mapParams) {
        return articleMapper.pageFindArticleList(mapParams);
    }

    /**
      *@Description: 统计分页
      *@Param: [mapParams]
      *@return: java.lang.Integer 
      *@Author: zyh
      *@Date: 2021/4/17 20:48
     **/
    @Override
    public Integer countPageSelectArticles(Map<String, Object> mapParams) {
        return articleMapper.countPageFindArticleList(mapParams);
    }

    @Override
    public Article getOneActicleById(Integer articleId) {
        return articleMapper.selectOneActicleById(articleId);
    }

    @Override
    public Integer countPersonSelectArticles(Map<String, Object> mapParams) {
        return articleMapper.countPersonOfArticles(mapParams);
    }

    @Override
    public List<Article> personSelectArticles(Map<String, Object> mapParams) {
        return articleMapper.selectOnePersonOfActicles(mapParams);
    }

    @Override
    public Boolean updateOneArticle(Article article) {
        article.setUpdateTime(new Date());
        articleMapper.updateArticle(article);
        return true;
    }

    @Override
    public Boolean deleteOneArticle(Integer articleId) {
        articleMapper.deletOneArticle(articleId);
        return true;
    }
}
