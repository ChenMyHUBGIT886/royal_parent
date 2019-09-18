package com.bbs.dao;

import com.bbs.domain.Reply;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ReplyDao {
    @Select("select * from bbs_reply_table where commentId = #{commentId} order by replyTime")
    @Results({
            @Result(property = "userInfo",column = "replyUserName",one = @One(
                    select = "com.bbs.dao.UserInfoDao.findByUserName"
            ))
    })
    List<Reply> findByCommentId(Integer commentId);

//    Reply{replyId=null, replyContent='asd', replyTime=null, replyUserName='admin', commentId=28}
    @Insert("insert into bbs_reply_table values(null,#{replyContent},null,#{replyUserName},#{commentId})")
    void addReply(Reply reply);
}
