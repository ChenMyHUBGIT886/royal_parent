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
    public List<Report> findByPage(int pageNum, int pageSize) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        return reportDao.findByPage();
    }

    /**
     * 屏蔽举报帖子
     *
     * @param articleId
     * @param isReport
     */
    @Override
    public void changeIsReport(int articleId, int isReport) throws Exception {
        reportDao.changeIsReport(articleId, isReport);
    }

    /**
     * 驳回举报帖子
     *
     * @param reportId
     */
    @Override
    public void changeStatus(int reportId) throws Exception {
        reportDao.changeStatus(reportId);
    }
}
