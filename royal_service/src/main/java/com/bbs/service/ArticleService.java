package com.bbs.service;

import com.bbs.domain.Article;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();

    List<Article> findByZoneId(Integer zoneId);

    Article getArticle(Integer articleId);

    Article getArticleDesc(Integer articleId);

    void save(Article article);

    Article findById(Integer articleId);

    List<Article> findLikeTitle(String title);

   Article findLikeComment(Integer articleId, String comment);

    void addReplyCount(Integer articleId);

    List<Article> findByPage(int pageNum, int pageSize) throws Exception;

    void changeStatus(Integer articleId, Integer isTop) throws Exception;

    void deleteArticle(Integer articleId) throws Exception;

    Article findByIdManager(Integer articleId) throws Exception;
}
