package com.bbs.controller;

import com.bbs.dao.ZoneDao;
import com.bbs.domain.Article;
import com.bbs.domain.Zone;
import com.bbs.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("zone")
public class ZoneController {
    @Autowired
    private ZoneService zoneService;
//此方法是登陆首页查询zone表第一条zone数据，内部有一个article集合，在下面展示
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name ="zoneId",required = true,defaultValue ="1")Integer zoneId){
        ModelAndView mv = new ModelAndView();
        Zone zone =zoneService.findById(zoneId);
        mv.addObject("zone",zone);
        mv.setViewName("index");
        return mv;
    }

}
