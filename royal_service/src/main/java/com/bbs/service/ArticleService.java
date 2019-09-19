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

    List<Article> findByPage(int pageNum, int pageSize);

    void changeStatus(Integer articleId, Integer isTop);

    void deleteArticle(Integer articleId);

    Article findByIdManager(Integer articleId);
    //模糊查询
    List<Article> findByCondition(Article article, int page, int size);
}
