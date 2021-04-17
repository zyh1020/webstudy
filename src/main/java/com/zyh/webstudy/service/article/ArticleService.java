package com.zyh.webstudy.service.article;

import com.zyh.webstudy.domain.article.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    public Boolean addOneArticle(Article article);
    public List<Article> pageSelectArticles(Map<String,Object> mapParams);
    public Integer countPageSelectArticles(Map<String,Object> mapParams);
}
