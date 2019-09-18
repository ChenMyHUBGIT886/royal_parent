package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.domain.UserInfo;
import com.bbs.service.ArticleService;
import com.bbs.service.CommentService;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

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
    List<UserInfo> userList = userService.findAllLoginStatus();
    mv.addObject("userStatusList",userList);
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
    public ModelAndView getArticle(Integer articleId){
        Article article = articleService.getArticle(articleId);
        article.setArticleId(articleId);
        ModelAndView mv = new ModelAndView();
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

    @RequestMapping("/findLikeComment.do")
    public ModelAndView findLikeTitle(Integer articleId,String comment){
        ModelAndView mv = new ModelAndView();
        Article article= articleService.findLikeComment(articleId,comment);
        mv.addObject("article",article);
        mv.setViewName("getArticle");
        return mv;
    }

}
