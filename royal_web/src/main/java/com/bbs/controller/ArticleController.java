package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.domain.UpVote;
import com.bbs.domain.UserInfo;
import com.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/findAll.do")
    @ResponseBody
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Article> list = articleService.findAll();
        mv.addObject("list",list);
        mv.setViewName("index");
        return mv;
    }
@RequestMapping("/findByZoneId.do")
    public ModelAndView findByZoneId(Integer zoneId) {
        List<Article> list = articleService.findByZoneId(zoneId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("articleList",list);
        mv.setViewName("index");
        return mv;
    }
     @RequestMapping("save.do")
    public  String save(Article article){
        articleService.save(article);
        Article article1=articleService.findById(article.getArticleId());
//         ModelAndView mv = new ModelAndView();
//         mv.addObject("article",article1);
//         mv.setViewName("getArticle");
        return "getArticle?articleId="+article1.getArticleId();
    }


    @RequestMapping("getArticle.do")
    public ModelAndView getArticle(Integer articleId, HttpServletRequest request){
        Article article = articleService.getArticle(articleId);
        article.setArticleId(articleId);
        ModelAndView mv = new ModelAndView();
        //此为点赞数统计，需要根据搭配帖子id去更改首页数据库数据
        Integer upVoteCount = articleService.getUpVoteCountByArticleId(articleId);
        if (upVoteCount == null) {
            upVoteCount = 0;
        }
        if (articleId == null) {
            articleId = 0;
        }
        //根据搭配帖子id去更改首页数据库数据
        articleService.setArticleUpVoteCount(articleId,upVoteCount);

        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        if (user != null) {
            String userName = user.getUserName();
            UpVote upVote = articleService.getUpVoteByArticleIdAndUserName(articleId,userName);
            mv.addObject("upvote",upVote);
        }
        mv.addObject("UpVoteCount",upVoteCount);
        mv.addObject("article",article);
        mv.setViewName("getArticle");
        return mv;
    }
    @RequestMapping("getArticleDesc.do")
    public ModelAndView getArticleDesc(Integer articleId){
        Article article = articleService.getArticleDesc(articleId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("article",article);
        mv.setViewName("getArticle");
        return mv;
    }

    //根据帖子账号和用户名查询数据库封装对象返回json到前台
    @RequestMapping("getReportByIdAndName.do")
    public @ResponseBody Boolean getReportByIdAndName(Integer articleId,HttpServletRequest request) {
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
//        System.out.println(articleId);
//        return false;
//        有用户登录的时候
        if (user != null) {
            String userName = user.getUserName();
            Article article = articleService.getReportByIdAndName(articleId,userName);
            //article对象有值的时候:
            if (article != null) {
                return true;
            }
            //article对象没有值的时候:
            else {
                return false;
            }
        } else {
            //没有用户登录的时候
            return false;
        }





    }

}
