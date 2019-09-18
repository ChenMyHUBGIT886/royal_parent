package com.bbs.service;

import com.bbs.domain.Article;
import com.bbs.domain.Report;

import java.util.List;

public interface ReportService {
    List<Report> findByPage(int pageNum, int pageSize);


}
