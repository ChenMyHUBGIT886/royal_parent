package com.bbs.controller;

import com.bbs.domain.UpVote;
import com.bbs.service.UpVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/upVote")
public class UpVoteController {
    @Autowired
    private UpVoteService upVoteService;

    //从ajax处获取到请求参数，传递到后台保存
    @RequestMapping("/saveByBean.do")
    public @ResponseBody UpVote saveByBean (@RequestBody UpVote upVote) {

        upVoteService.saveByBean(upVote);
        return upVote;
    }

    //从ajax处获取到请求参数，传递到后台更新点赞状态
    @RequestMapping("/changeIsUpvote.do")
    public @ResponseBody UpVote changeIsUpvote (@RequestBody UpVote upVote) {
        upVoteService.changeIsUpvote(upVote);
        return upVote;
    }
}
