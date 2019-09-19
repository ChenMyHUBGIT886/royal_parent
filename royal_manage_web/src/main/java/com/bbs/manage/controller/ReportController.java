package com.bbs.manage.controller;

import com.bbs.domain.Report;
import com.bbs.service.ReportService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 查询所有审批举报
     *
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping("/findByPage.do")
    public ModelAndView findByPage(
            @RequestParam(name = "pageNum", required = true, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = true, defaultValue = "5") int pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Report> reportList = reportService.findByPage(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(reportList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("ReportPage");
        return mv;
    }

    /**
     * 屏蔽举报帖子
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/changeIsReport.do")
    public String changeIsReport(int articleId, int isReport, int pageNum) throws Exception {
        reportService.changeIsReport(articleId, isReport);
        return "redirect:findByPage.do?pageNum=" + pageNum;
    }

    /**
     * 驳回举报帖子
     *
     * @param reportId
     */
    @RequestMapping("/changeStatus.do")
    public String changeStatus(int reportId) throws Exception {
        reportService.changeStatus(reportId);
        return "redirect:findByPage.do";
    }
}
