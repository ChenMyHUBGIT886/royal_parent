package com.bbs.controller;

import com.bbs.dao.ZoneDao;
import com.bbs.domain.Article;
import com.bbs.domain.Zone;
import com.bbs.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("zone")
public class ZoneController {
    @Autowired
    private ZoneService zoneService;
//此方法是登陆首页查询zone表第一条zone数据，内部有一个article集合，在下面展示
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        Integer zoneId=1;
        Zone zone =zoneService.findAll(zoneId);
        mv.addObject("zone",zone);
        mv.setViewName("index");
        return mv;
    }
}
