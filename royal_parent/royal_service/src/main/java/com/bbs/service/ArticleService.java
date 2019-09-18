package com.bbs.service;

import com.bbs.domain.Article;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();

    List<Article> findByPage(int pageNum, int pageSize);

    void changeStatus(Integer articleId, Integer isTop);

    void deleteArticle(Integer articleId);

    Article findByIdManager(Integer articleId);
}
