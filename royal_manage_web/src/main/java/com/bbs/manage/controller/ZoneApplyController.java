package com.bbs.manage.controller;

import com.bbs.domain.ZoneApply;
import com.bbs.service.ZoneService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/zoneApply")
public class ZoneApplyController {
    @Autowired
    private ZoneService zoneService;
    @RequestMapping("findByPage.do")
    public ModelAndView findByPage(@RequestParam(value = "pageNum",required = true,defaultValue = "1")Integer pageNum,
                                   @RequestParam(value = "pageSize",required = true,defaultValue = "3")Integer pageSize) {
        ModelAndView mv = new ModelAndView();
        //分页查询所有状态值为0的版块数据
        List<ZoneApply> zoneApplyList = zoneService.findByPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(zoneApplyList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("ZoneApplyPage");
        return mv;
    }

    @RequestMapping("addZoneApplyStatus.do")
    public String addZoneApplyStatus(@RequestParam(value = "applyZoneId")Integer applyZoneId,
                                        @RequestParam(value = "pageNum")Integer pageNum,
                                     @RequestParam(value = "zoneName")String zoneName) {
        //点通过后给zone表内添加一个板块数据
        zoneService.addZoneByZoneName(zoneName);
        //点通过后更改zoneApply表内status的状态值
        zoneService.changeZoneApplyStatus(applyZoneId);
        return "redirect:findByPage.do?pageNum="+pageNum;
    }

    @RequestMapping("changeStatus.do")
    public String changeStatus(@RequestParam(value = "applyZoneId")Integer applyZoneId,@RequestParam(value = "pageNum")Integer pageNum) {
        //点通过后更改zoneApply表内status的状态值
        zoneService.changeZoneApplyStatus(applyZoneId);
        return "redirect:findByPage.do?pageNum=" + pageNum;
    }
}
