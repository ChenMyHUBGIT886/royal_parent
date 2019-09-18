package com.bbs.service.impl;

import com.bbs.dao.ArticleDao;
import com.bbs.domain.Article;
import com.bbs.service.ArticleService;
import com.github.pagehelper.PageHelper;
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

    /**
     * 查询所有帖子信息（后台）
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<Article> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return articleDao.findAllManager();
    }

    /**
     * 帖子置顶
     *
     * @param articleId
     * @param isTop
     */
    @Override
    public void changeStatus(Integer articleId, Integer isTop) {
        articleDao.changeStatus(articleId, isTop);
    }

    /**
     * 帖子删除
     *
     * @param articleId
     */
    @Override
    public void deleteArticle(Integer articleId) {
        articleDao.deleteArticle(articleId);
    }

    /**
     * 相关帖子
     *
     * @param articleId
     * @return
     */
    @Override
    public Article findByIdManager(Integer articleId) {
        return articleDao.findByIdManager(articleId);
    }
}
