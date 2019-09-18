package com.bbs.dao;

import com.bbs.domain.Article;
import com.bbs.domain.Report;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportDao {
    @Select("select * from bbs_report_table where articleId in (select articleId from bbs_article_table where isReport = 1)")
    List<Report> findAll();


}
