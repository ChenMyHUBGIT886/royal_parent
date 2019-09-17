package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface ArticleDao {

    @Select("select * from bbs_article_table")
    List<Article> findAll();

    @Select("select * from bbs_article_table where zoneId=#{id} order by isTop desc")
    List<Article> findByZoneId(Integer id);

    @Select("select * from bbs_article_table where articleId = #{articleId}")
    @Results({
            @Result(property = "userInfo",column = "senderName" ,javaType = UserInfo.class,
                one = @One(select = "com.bbs.dao.UserInfoDao.findByUserName")
            ),
            @Result(property = "comments",column ="articleId",many = @Many(
                    select = "com.bbs.dao.CommentDao.findAllByArticleId"
            ))
    })
    Article getArticle(Integer articleId);

    @Select("select * from bbs_article_table where articleId = #{articleId}")
    @Results({
            @Result(id = true,property = "articleId",column = "articleId"),
            @Result(property = "userInfo",column = "senderName" ,javaType = UserInfo.class,
                    one = @One(select = "com.bbs.dao.UserInfoDao.findByUserName")
            ),
            @Result(property = "comments",column ="articleId",many = @Many(
                    select = "com.bbs.dao.CommentDao.findAllByArticleIdDesc"
            ))
    })
    Article getArticleDesc(Integer articleId);
    @Select("select * from bbs_article_table where zoneId=#{id}")
    List<Article> findByZoneid(Integer id);
    @Insert("insert into bbs_article_table(title,content,sendTime,senderName,isTop,replyCount,upvoteCount,browseCount,zoneId,isReport) " +
            "values(#{title},#{content},null,#{senderName},0,0,0,0,#{zoneId},0)")
    @SelectKey(keyProperty = "articleId",keyColumn = "articleId",before = false,resultType = int.class,statement = "select last_insert_id()")
    void save(Article article);


    @Select("select * from bbs_article_table where articleId=#{articleId}")
    Article findById(Integer articleId);
}
