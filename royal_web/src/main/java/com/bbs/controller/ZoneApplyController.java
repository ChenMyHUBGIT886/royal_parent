package com.bbs.controller;

import com.bbs.domain.ZoneApply;
import com.bbs.service.ZoneApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("zoneApply")
public class ZoneApplyController {
    @Autowired
     private  ZoneApplyService zoneApplyService;
    @RequestMapping("save.do")
    @ResponseBody
    public String save(@RequestBody ZoneApply zoneApply){
        zoneApplyService.save(zoneApply);
        return  "{\"msg\":\"1\"}";
    }
}
