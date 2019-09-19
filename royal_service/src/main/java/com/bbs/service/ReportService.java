package com.bbs.service;

import com.bbs.domain.Report;

import java.util.List;

public interface ReportService {
    List<Report> findByPage(int pageNum, int pageSize) throws Exception;

    void changeIsReport(int articleId, int isReport) throws Exception;

    void changeStatus(int reportId) throws Exception;
}
