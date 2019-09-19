package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.Report;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ReportDao {
    //分页查询所有审核举报（后台）
//    @Select("select * from bbs_report_table where articleId in (select articleId from bbs_article_table where isReport = 0)")
    @Select("select * from bbs_report_table where reportStatus = 0")
    @Results({
            @Result(id = true, property = "reportId", column = "reportId"),
            @Result(property = "reportContent", column = "reportContent"),
            @Result(property = "reportTime", column = "reportTime"),
            @Result(property = "reportUserName", column = "reportUserName"),
            @Result(property = "reportStatus", column = "reportStatus"),
            @Result(property = "articleId", column = "articleId"),
            @Result(property = "article", column = "articleId", javaType = Article.class,
                    one = @One(select = "com.bbs.dao.ArticleDao.findById"))
    })
    List<Report> findByPage() throws Exception;

    //屏蔽举报帖子
    @Update("update bbs_article_table set isReport = #{isReport} where articleId = #{articleId}")
    void changeIsReport(@Param("articleId") int articleId, @Param("isReport") int isReport) throws Exception;

    //驳回举报帖子
    @Update("update bbs_report_table set reportStatus = 1 where reportId = #{reportId}")
    void changeStatus(int reportId) throws Exception;
}
