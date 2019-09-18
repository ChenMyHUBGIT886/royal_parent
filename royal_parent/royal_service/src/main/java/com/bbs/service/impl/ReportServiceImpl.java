package com.bbs.service.impl;

import com.bbs.dao.ReportDao;
import com.bbs.domain.Article;
import com.bbs.domain.Report;
import com.bbs.service.ReportService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDao reportDao;

    /**
     * 查询所有审批举报
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<Report> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return reportDao.findAll();
    }


}
