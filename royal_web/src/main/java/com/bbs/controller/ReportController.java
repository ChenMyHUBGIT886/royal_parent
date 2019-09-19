package com.bbs.controller;

import com.bbs.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    //根据举报框内的内容和帖子编号封装一个Report对象保存到数据库中
    @RequestMapping("saveReportInfoByIdAndValue.do")
    public void saveReportInfoByIdAndValue(@RequestParam(value = "reportContent")String reportValue,
                                             @RequestParam(value = "articleId4Report")Integer articleId, HttpServletRequest request, HttpServletResponse response) throws Exception {
            reportService.saveReportInfoByIdAndValue(reportValue,articleId,request);
//            response.sendRedirect( request.getContextPath()+"/jsp/reportSuccess.jsp/");


    }
}
