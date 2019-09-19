package com.bbs.service.impl;

import com.bbs.dao.ArticleDao;
import com.bbs.domain.Article;
import com.bbs.domain.UpVote;
import com.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public List<Article> findAll() {
        return articleDao.findAll();
    }

    @Override
    public List<Article> findByZoneId(Integer zoneId) {
        return articleDao.findByZoneId(zoneId);
    }

    @Override
    public Article getArticle(Integer articleId) {
        return articleDao.getArticle(articleId);
    }

    @Override
    public Article getArticleDesc(Integer articleId) {
        return articleDao.getArticleDesc(articleId);
    }

    @Override
    public void save(Article article) {
        articleDao.save(article);
    }

    @Override
    public Article findById(Integer articleId) {
        Article article=articleDao.findById(articleId);

        return article;
    }

    @Override
    public UpVote getUpVoteByArticleIdAndUserName(Integer articleId, String userName) {
        return articleDao.getUpVoteByArticleIdAndUserName(articleId,userName);
    }

    @Override
    public Integer getUpVoteCountByArticleId(Integer articleId) {
        return articleDao.getUpVoteCountByArticleId(articleId);
    }

    @Override
    public void setArticleUpVoteCount(Integer articleId, Integer upVoteCount) {
        articleDao.setArticleUpVoteCount(articleId,upVoteCount);
    }

    @Override
    public Article getReportByIdAndName(Integer articleId, String userName) {
        return articleDao.getReportByIdAndName(articleId,userName);
    }

}
