package com.bbs.dao;

import com.bbs.domain.UpVote;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UpVoteDao {

    @Insert("insert into bbs_upvote_table values(#{upvoteUserName},#{upvoteArticleId},#{isUpvote})")
    void saveByBean(UpVote upVote);

    //根据用户名和帖子编号改变点赞的状态值
    @Update("update bbs_upvote_table set isupvote = #{isUpvote} where upvoteusername = #{upvoteUserName} and upvotearticleid = #{upvoteArticleId}")
    void changeIsUpvote(UpVote upVote);
}
