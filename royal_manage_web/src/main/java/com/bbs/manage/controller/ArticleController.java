package com.bbs.manage.controller;

import com.bbs.domain.Article;
import com.bbs.domain.Report;
import com.bbs.service.ArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 查询所有帖子（后台）
     *
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping("/findByPage.do")
    public ModelAndView findByPage(
            @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Article> articleList = articleService.findByPage(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(articleList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("ArticlePage");
        return mv;
    }

    /**
     * 帖子置顶
     *
     * @param articleId
     * @param isTop
     */
    @RequestMapping("/changeStatus.do")
    public String changeStatus(
            @RequestParam(name = "id", required = true) Integer articleId,
            @RequestParam(name = "isTop", required = true) Integer isTop) {
        articleService.changeStatus(articleId, isTop);
        return "redirect:findByPage.do";
    }

    /**
     * 帖子删除
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/deleteArticle.do")
    public String deleteArticle(@RequestParam(name = "id", required = true) Integer articleId) {
        articleService.deleteArticle(articleId);
        return "redirect:findByPage.do";
    }

    /**
     * 相关帖子
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/findByIdManager.do")
    public void findByIdManager(Integer articleId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Article article = articleService.findByIdManager(articleId);
        request.getSession().setAttribute("article", article);
        System.out.println(article);

    }
}
