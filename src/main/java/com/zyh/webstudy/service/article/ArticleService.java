package com.zyh.webstudy.service.article;

import com.zyh.webstudy.domain.article.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    Boolean addOneArticle(Article article);
    List<Article> pageSelectArticles(Map<String,Object> mapParams);
    Integer countPageSelectArticles(Map<String,Object> mapParams);
    Article getOneActicleById(Integer articleId);
    Integer countPersonSelectArticles(Map<String, Object> mapParams);
    List<Article> personSelectArticles(Map<String, Object> mapParams);
    Boolean updateOneArticle(Article article);
    Boolean deleteOneArticle(Integer articleId);
}
