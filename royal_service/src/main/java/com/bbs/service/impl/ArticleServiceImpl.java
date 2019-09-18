package com.bbs.service.impl;

import com.bbs.dao.ArticleDao;
import com.bbs.domain.Article;
import com.bbs.domain.Comment;
import com.bbs.service.ArticleService;
import com.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private CommentService commentService;

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
    public List<Article> findLikeTitle(String title) {
        title = "%"+title+"%";
        return articleDao.findLikeTitle(title);
    }

    @Override
    public Article findLikeComment(Integer articleId,String comment) {
        comment = "%"+comment+"%";
        Article article = articleDao.findLikeComment(articleId);
        List<Comment> comments = commentService.findLikeComment(comment);
        article.setComments(comments);
        return article;

    }

    @Override
    public void addReplyCount(Integer articleId) {
        Article article = articleDao.findReplyCount(articleId);
        articleDao.addReplyCount(article.getReplyCount()+1);
    }

}
