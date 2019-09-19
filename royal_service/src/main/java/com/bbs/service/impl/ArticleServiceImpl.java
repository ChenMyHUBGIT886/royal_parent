package com.bbs.service.impl;

import com.bbs.dao.ArticleDao;
import com.bbs.domain.Article;
import com.bbs.domain.Comment;
import com.bbs.domain.Reply;
import com.bbs.domain.Word;
import com.bbs.service.ArticleService;
import com.bbs.service.CommentService;
import com.bbs.service.WordService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private CommentService commentService;
    @Autowired
    private WordService wordService;

    @Override
    public List<Article> findAll() {
        return articleDao.findAll();
    }

    @Override
    public List<Article> findByZoneId(Integer zoneId) {
        List<Article> articleList = articleDao.findByZoneId(zoneId);
       List<String> wordList = wordService.findByWordStatus();
        List<Article> articles = filtrationWord(articleList, wordList);
        return articles;
    }

    @Override
    public Article getArticle(Integer articleId) {
        Article article = articleDao.getArticle(articleId);
        ArrayList<Article> arrayList = new ArrayList<>();
        arrayList.add(article);
        List<String> wordList = wordService.findByWordStatus();
        List<Article> articles = filtrationWord(arrayList, wordList);
        return articles.get(0);
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

    /**
     * 查询所有帖子信息（后台）
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<Article> findByPage(int pageNum, int pageSize) throws Exception {
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
    public void changeStatus(Integer articleId, Integer isTop) throws Exception {
        articleDao.changeStatus(articleId, isTop);
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

    /**
     * 帖子删除
     *
     * @param articleId
     */
    @Override
    public void deleteArticle(Integer articleId) throws Exception {
        articleDao.deleteArticle(articleId);
    }

    /**
     * 相关帖子
     *
     * @param articleId
     * @return
     */
    @Override
    public Article findByIdManager(Integer articleId) throws Exception {
        return articleDao.findByIdManager(articleId);
    }


    public List<Article> filtrationWord( List<Article> articleList ,List<String> wordList){
        if (articleList != null && wordList !=null) {
            for (Article article : articleList) {
                String title = article.getTitle();
                for (String word : wordList) {
                    if (title.contains(word)) {
                       article.setTitle(title.replace(word,"***"));
                    }
                }

                String content = article.getContent();
                for (String word : wordList) {
                    if (content.contains(word)) {
                        article.setContent(content.replace(word,"***"));
                    }
                }


                List<Comment> comments = article.getComments();
                if (comments != null) {
                    for (Comment comment : comments) {
                        String commentContent = comment.getCommentContent();
                        for (String word : wordList) {
                            if (commentContent.contains(word)) {
                                comment.setCommentContent(commentContent.replace(word,"***"));
                            }
                        }


                        List<Reply> replyList = comment.getReplyList();
                        if (replyList != null) {
                            for (Reply reply : replyList) {
                                for (String word : wordList) {
                                    String replyContent = reply.getReplyContent();
                                    if (replyContent.contains(word)) {
                                        reply.setReplyContent(replyContent.replace(word,"***"));
                                    }
                                }
                            }
                        }
                        comment.setReplyList(replyList);
                    }
                }
                article.setComments(comments);
            }
        }

        return articleList;
    }



    @Override
    public List<Article> findByCondition(Article article, int page, int size) {
        String senderName = article.getSenderName();
        String title = article.getTitle();
        if(senderName==null){
            senderName="";
        }
        article.setSenderName(senderName.trim());
        article.setTitle(title.trim());
        PageHelper.startPage(page, size);
        return articleDao.findByCondition(article);
    }

    @Override
    public List<Article> findByTime() {
       List<Article> list2=articleDao.findByTime();

        return list2;
    }

    @Override
    //查询发帖总数
    public Integer findCount(String userName) {
      Integer aticleCount= articleDao.findCount(userName);

        return aticleCount;
    }
}
