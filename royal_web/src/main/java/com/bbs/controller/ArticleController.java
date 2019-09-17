package com.bbs.controller;

import com.bbs.domain.Article;
import com.bbs.service.ArticleService;
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

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Article> list = articleService.findAll();
        mv.addObject("list",list);
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



}
