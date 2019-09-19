package com.bbs.controller;

import com.bbs.domain.Info;
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

    /*//从ajax处获取到请求参数，传递到后台保存
    @RequestMapping("/saveByBean.do")
    public @ResponseBody Boolean saveByBean (@RequestBody UpVote upVote) {
        //判断后台是否有数据
        Integer isUpvote = upVoteService.findByIdAndName(upVote.getUpvoteArticleId(),upVote.getUpvoteUserName());
        Integer num = upVote.getIsUpvote();
        //无数据的时候保存并返回true
        if (isUpvote == null) {
            upVoteService.saveByBean(upVote);
            return true;
        } else {
            //有数据的时候采取下列的返回方法返回
            //对比数据库的isupvote是否和前台的相同
            //相同数据库改0返回false
            if (isUpvote == num) {
                upVote.setIsUpvote(0);
                upVoteService.changeIsUpvote(upVote);
                return false;
            } else {
                //不同，数据库改1，返回true
                upVote.setIsUpvote(1);
                upVoteService.changeIsUpvote(upVote);
                return true;
            }
        }
    }*/

     //从ajax处获取到请求参数，传递到后台保存
    @RequestMapping("/saveByBean.do")
    public @ResponseBody Info saveByBean (@RequestBody UpVote upVote) {
        //判断后台是否有数据
        Integer isUpvote = upVoteService.findByIdAndName(upVote.getUpvoteArticleId(),upVote.getUpvoteUserName());
        Integer num = upVote.getIsUpvote();
        Info info = new Info();
        //无数据的时候保存并返回true
        if (isUpvote == null) {
            upVoteService.saveByBean(upVote);
            //去后台查总赞数
            Integer count = upVoteService.getUpVoteCount(upVote.getUpvoteArticleId());
            info.setIsUpVote(count);
            info.setBoo(true);
            return info;
        } else {
            //有数据的时候采取下列的返回方法返回
            //对比数据库的isupvote是否和前台的相同
            //相同数据库改0返回false
            if (isUpvote == num) {
                upVote.setIsUpvote(0);
                upVoteService.changeIsUpvote(upVote);
                info.setBoo(false);
                //去后台查总赞数
                Integer count = upVoteService.getUpVoteCount(upVote.getUpvoteArticleId());
                info.setIsUpVote(count);
                return info;
            } else {
                //不同，数据库改1，返回true
                upVote.setIsUpvote(1);
                upVoteService.changeIsUpvote(upVote);
                info.setBoo(true);
                //去后台查总赞数
                Integer count = upVoteService.getUpVoteCount(upVote.getUpvoteArticleId());
                info.setIsUpVote(count);
                return info;
            }
        }
    }



    //从ajax处获取到请求参数，传递到后台更新点赞状态
    /*@RequestMapping("/changeIsUpvote.do")
    public @ResponseBody Boolean changeIsUpvote (@RequestBody UpVote upVote) {
        //对比数据库的isupvote是否和前台的相同
        Integer isUpvote = upVoteService.findByIdAndName(upVote.getUpvoteArticleId(),upVote.getUpvoteUserName());
        Integer num = upVote.getIsUpvote();
        //相同就返回true
        if (isUpvote == num) {
            if ( 0 == num) {
                upVote.setIsUpvote(1);
            } else if (1 == num) {
                upVote.setIsUpvote(0);
            }
            upVoteService.changeIsUpvote(upVote);
            return true;
        }
        //不同就更改数据库的值返回false
        else {
            upVoteService.changeIsUpvote(upVote);
            return false;
        }
    }*/
    @RequestMapping("/changeIsUpvote.do")
    public @ResponseBody Info changeIsUpvote (@RequestBody UpVote upVote) {
        //对比数据库的isupvote是否和前台的相同
        Integer isUpvote = upVoteService.findByIdAndName(upVote.getUpvoteArticleId(),upVote.getUpvoteUserName());
        Info info = new Info();
        Integer num = upVote.getIsUpvote();
        //相同就返回true
        if (isUpvote == num) {
            if ( 0 == num) {
                upVote.setIsUpvote(1);
            } else if (1 == num) {
                upVote.setIsUpvote(0);
            }
            upVoteService.changeIsUpvote(upVote);
            info.setBoo(true);
            //去后台查总赞数
            Integer count = upVoteService.getUpVoteCount(upVote.getUpvoteArticleId());
            info.setIsUpVote(count);
            return info;
        }
        //不同就更改数据库的值返回false
        else {
            upVoteService.changeIsUpvote(upVote);
            info.setBoo(false);
            Integer count = upVoteService.getUpVoteCount(upVote.getUpvoteArticleId());
            info.setIsUpVote(count);
            return info;
        }
    }

}
