package com.bbs.dao;

import com.bbs.domain.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ArticleDao {

    @Select("select * from bbs_article_table")
    List<Article> findAll();

    @Select("select * from bbs_article_table where zoneId=#{id}")
    List<Article> findByZoneid(Integer id);

    //查询所有帖子（后台）
    @Select("select * from bbs_article_table order by isTop desc")
    List<Article> findAllManager();

    //后台帖子置顶
    @Update("update bbs_article_table set isTop = #{isTop} where articleId = #{articleId}")
    void changeStatus(@Param("articleId") Integer articleId, @Param("isTop") Integer isTop);

    //后台根据Id删除帖子
    @Delete("delete from bbs_article_table where articleId = #{articleId}")
    void deleteArticle(Integer articleId);

    //后台根据Id查询帖子
    @Select("select * from bbs_article_table where articleId = #{articleId}")
    Article findByIdManager(Integer articleId);
}
