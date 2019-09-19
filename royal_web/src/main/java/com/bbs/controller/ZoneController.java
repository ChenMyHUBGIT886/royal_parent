package com.bbs.controller;

import com.bbs.dao.ZoneDao;
import com.bbs.domain.Article;
import com.bbs.domain.UserInfo;
import com.bbs.domain.Zone;
import com.bbs.service.ArticleService;
import com.bbs.service.UserService;
import com.bbs.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("zone")
public class ZoneController {
    @Autowired
    private ZoneService zoneService;
    @Autowired
    private ArticleController articleController;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(HttpServletRequest request){
        List<Zone> list = zoneService.findAll();
        request.getSession().setAttribute("zoneList",list);
        ModelAndView mv = articleController.findByZoneId(1);

        return mv;
    }

    @RequestMapping("/findLikeTitle.do")
    public ModelAndView findLikeTitle(String title){
        ModelAndView mv = new ModelAndView();
        List<Article> list = articleService.findLikeTitle(title);
        mv.addObject("titleList",list);
        mv.setViewName("findResult");
        return mv;
    }
}
