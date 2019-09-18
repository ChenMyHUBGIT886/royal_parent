package com.bbs.service.impl;

import com.bbs.dao.ReportDao;
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
        return reportDao.findByPage();
    }

    /**
     * 屏蔽举报帖子
     *
     * @param articleId
     */
    @Override
    public void changeIsReport(int articleId) {
        reportDao.changeIsReport(articleId);
    }

    /**
     * 驳回举报帖子
     *
     * @param reportId
     */
    @Override
    public void changeStatus(int reportId) {
        reportDao.changeStatus(reportId);
    }
}
