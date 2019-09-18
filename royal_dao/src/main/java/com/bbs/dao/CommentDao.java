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
//Comment{commentId=null, commentContent='1', commentTime=null, commentUserName='admin', commentStatus=null, articleId=12, replyList=null}
    @Insert("insert into bbs_comment_table values(null,#{commentContent},null,#{commentUserName},0,#{articleId})")
    void addComment(Comment comment);

    @Select("select * from bbs_comment_table where commentContent like #{comment} order by commentTime")
    @Results({
            @Result(property = "userInfo",column = "commentUserName",one = @One(
                    select = "com.bbs.dao.UserInfoDao.findByUserName"
            ))
    })
    List<Comment> findLikeComment(String  comment);
}
