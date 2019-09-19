package com.bbs.service.impl;

import com.bbs.dao.ReportDao;
import com.bbs.domain.Report;
import com.bbs.domain.UserInfo;
import com.bbs.service.ReportService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDao reportDao;

    //查询所有审批举报
    @Override
    public List<Report> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return reportDao.findByPage();
    }

    //根据举报框内的内容和帖子编号封装一个Report对象保存到数据库中
    @Override
    public void saveReportInfoByIdAndValue(String reportValue, Integer articleId , HttpServletRequest request) {
        Report report = new Report();
        report.setArticleId(articleId);
        report.setReportContent(reportValue);
        report.setReportStatus(0);
        report.setReportTime(new Date());
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        String userName = user.getUserName();
        report.setReportUserName(userName);
        reportDao.saveReportInfoByIdAndValue(report);
    }
}
