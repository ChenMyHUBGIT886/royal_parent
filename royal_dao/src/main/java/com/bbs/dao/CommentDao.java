package com.bbs.dao;

import com.bbs.domain.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommentDao {
    @Select("select * from bbs_comment_table where articleId = #{articleId} order by commentTime")
    @Results({
            @Result( id = true, property = "commentId",column = "commentId"),
            @Result(property = "userInfo",column = "commentUserName",one = @One(
                    select = "com.bbs.dao.UserInfoDao.findByUserName"
            )),
            @Result(property = "replyList",column = "commentId",many = @Many(
                    select = "com.bbs.dao.ReplyDao.findByCommentId"
            ))
    })
    List<Comment> findAllByArticleId(Integer articleId);


    @Select("select * from bbs_comment_table where articleId = #{articleId} order by commentTime desc")
    @Results({
            @Result( id = true, property = "commentId",column = "commentId"),
            @Result(property = "userInfo",column = "commentUserName",one = @One(
                    select = "com.bbs.dao.UserInfoDao.findByUserName"
            )),
            @Result(property = "replyList",column = "commentId",many = @Many(
                    select = "com.bbs.dao.ReplyDao.findByCommentId"
            ))
    })
    List<Comment> findAllByArticleIdDesc(Integer articleId);
}
