package com.bbs.service;

import com.bbs.domain.Article;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();

    void save(Article article);

    Article findById(Integer articleId);
}
