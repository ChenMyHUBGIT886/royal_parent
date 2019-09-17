package com.bbs.dao;

import com.bbs.domain.Reply;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReplyDao {
    @Select("select * from bbs_reply_table where commentId = #{commentId} order by replyTime")
    @Results({
            @Result(property = "userInfo",column = "replyUserName",one = @One(
                    select = "com.bbs.dao.UserInfoDao.findByUserName"
            ))
    })
    List<Reply> findByCommentId(Integer commentId);
}
