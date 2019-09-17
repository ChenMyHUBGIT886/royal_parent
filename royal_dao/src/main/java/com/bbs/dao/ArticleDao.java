package com.bbs.dao;

import com.bbs.domain.Article;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleDao {

    @Select("select * from bbs_article_table")
    List<Article> findAll();

    @Select("select * from bbs_article_table where zoneId=#{id}")
    List<Article> findByZoneId(Integer id);
}
