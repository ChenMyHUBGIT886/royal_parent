package com.bbs.dao;

import com.bbs.domain.Report;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReportDao {
    //分页查询所有审核举报（后台）
//    @Select("select * from bbs_report_table where articleId in (select articleId from bbs_article_table where isReport = 0)")
    @Select("select * from bbs_report_table where reportStatus = 0")
    List<Report> findByPage();

    //屏蔽举报帖子
    @Update("update bbs_article_table set isReport = 1 where articleId = #{articleId}")
    void changeIsReport(int articleId);

    //驳回举报帖子
    @Update("update bbs_report_table set reportStatus = 1 where reportId = #{reportId}")
    void changeStatus(int reportId);
}
