package com.bbs.service;

import com.bbs.domain.Article;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();

    List<Article> findByZoneId(Integer zoneId);

    Article getArticle(Integer articleId);

    Article getArticleDesc(Integer articleId);
}
