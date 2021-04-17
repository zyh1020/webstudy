package com.zyh.webstudy.mapper.article;

import com.zyh.webstudy.domain.article.Article;

import java.util.List;
import java.util.Map;

public interface ArticleMapper {
    void insertOneArticle(Article article);
    List<Article> pageFindArticleList(Map<String,Object> mapParams);
    Integer countPageFindArticleList(Map<String,Object> mapParams);
}
