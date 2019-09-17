package com.bbs.dao;

import com.bbs.domain.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface ArticleDao {

    @Select("select * from bbs_article_table")
    List<Article> findAll();
    @Select("select * from bbs_article_table where zoneId=#{id}")
    List<Article> findByZoneid(Integer id);
    @Insert("insert into bbs_article_table(title,content,sendTime,senderName,isTop,replyCount,upvoteCount,browseCount,zoneId,isReport) " +
            "values(#{title},#{content},null,#{senderName},0,0,0,0,#{zoneId},0)")
    @SelectKey(keyProperty = "articleId",keyColumn = "articleId",before = false,resultType = int.class,statement = "select last_insert_id()")
    void save(Article article);


    @Select("select * from bbs_article_table where articleId=#{articleId}")
    Article findById(Integer articleId);
}
