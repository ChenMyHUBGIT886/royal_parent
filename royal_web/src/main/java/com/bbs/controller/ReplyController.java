package com.bbs.controller;

import com.bbs.domain.Reply;
import com.bbs.service.ReplyService;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;
    @Autowired
    private UserService userService;

    @RequestMapping("/addReply.do")
    @ResponseBody
    public Map<String,Integer> addReply(Reply reply){
        HashMap<String, Integer> map = new HashMap<>();
        if (reply.getReplyContent().isEmpty()){
            map.put("msg",0);
            return map;
        }

        if (!userService.findTalkStatusByName(reply.getReplyUserName())) {
            map.put("msg",3);
            return map;
        }
        replyService.addReply(reply);
        map.put("msg",1);
        return map;


    }
}
