package com.bbs.controller;

import com.bbs.domain.Comment;
import com.bbs.service.ArticleService;
import com.bbs.service.CommentService;
import com.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/addComment.do")
    public Map<String, Integer> addComment(Comment comment){
        Map<String, Integer> map = new HashMap<>();
        if (!userService.findTalkStatusByName(comment.getCommentUserName())) {
            map.put("msg",0);
            return map;
        }

        commentService.addComment(comment);
        articleService.addReplyCount(comment.getArticleId());
        map.put("msg",1);
        return map;
    }

}
