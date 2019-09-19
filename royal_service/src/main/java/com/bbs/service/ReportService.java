package com.bbs.service;

import com.bbs.domain.Report;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ReportService {
    List<Report> findByPage(int pageNum, int pageSize);

    void saveReportInfoByIdAndValue(String reportValue, Integer articleId, HttpServletRequest request);
}
