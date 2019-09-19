package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.UpVote;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();

    List<Article> findByZoneId(Integer zoneId);

    Article getArticle(Integer articleId);

    Article getArticleDesc(Integer articleId);

    void save(Article article);

    Article findById(Integer articleId);

    UpVote getUpVoteByArticleIdAndUserName(Integer articleId, String userName);

    Integer getUpVoteCountByArticleId(Integer articleId);

    void setArticleUpVoteCount(Integer articleId, Integer upVoteCount);

    Article getReportByIdAndName(Integer articleId, String userName);
}
